/*
 * Copyright 2014 Xored Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xored.scalajs.react.internal

import scala.reflect.macros.whitebox._
import scala.language.experimental.macros
import scala.util.matching.Regex
import scala.xml._
import com.xored.scalajs.react.ReactDOM
import scala.collection.mutable
import scala.annotation.StaticAnnotation

object ScalaxImpl {

  def macroTransform(c: Context)(annottees: c.Expr[Any]*): c.Expr[ReactDOM] = {
    import c.universe._

    val h = new ScalaxHelper[c.type](c)
    val newTrees = annottees.map(expr => h.transform(expr.tree))

    val verbose = c.macroApplication match {
      case q"new $x($arg).macroTransform(..$y)" => c.eval(c.Expr[Boolean](arg))
      case _ => false
    }

    if (verbose) {
      val msg = newTrees.mkString("\n\n\n")
      c.info(c.enclosingPosition, msg, force = true)
    }

    c.Expr[ReactDOM](q"..$newTrees")
  }

  def apply(c: Context)(elem: c.Expr[Elem]): c.Expr[ReactDOM] = {
    val h = new ScalaxHelper[c.type](c)
    val newTree = h.transform(elem.tree)

    val checked = c.typecheck(c.untypecheck(newTree))
    c.Expr(checked)
  }
}

private class ScalaxHelper[C <: Context](val c: C) {

  import c.universe._

  class HasType[T](implicit t: c.WeakTypeTag[T]) {

    def matches(tree: Tree) = {
      c.Expr(c.typecheck(tree, silent = true)).actualType =:= t.tpe ||
        c.Expr(c.typecheck(tree, mode = c.TYPEmode, silent = true)).actualType =:= t.tpe
    }

    def unapply(tree: Tree): Option[Tree] = {
      if (matches(tree)) Some(tree)
      else None
    }
  }

  val hasNodeBufferType = new HasType[NodeBuffer]
  val scalaXmlNull = new HasType[scala.xml.Null.type]
  val hasMetaDataType = new HasType[MetaData]
  val hasElemType = new HasType[Elem]
  val hasTextType = new HasType[Text]
  val hasStringType = new HasType[String]
  val hasAttributeType = new HasType[UnprefixedAttribute]

  val ReactDOMBufferTpe = weakTypeOf[ReactDOMBuffer]
  val ReactDOMTpe = weakTypeOf[ReactDOM]

  val ReactDOMCompanionSym = symbolOf[ReactDOM].companion
  val ReactMetadataCompanionSym = symbolOf[ReactMetaData].companion

  def isWhitespace(str: String) = new Regex( """^\s*$""", "m").unapplySeq(str).isDefined

  def transform(tree: Tree): Tree = ElemTransformer.transform(tree)

  object ElemTransformer extends Transformer {
    override def transform(tree: Tree): Tree = tree match {

      case q"$mods val $name: $tpt = $tpe" if hasNodeBufferType.matches(tpe) =>
        q"$mods val $name = new $ReactDOMBufferTpe()"

      case q"$mods var $name: $tpt = $tpe" if scalaXmlNull.matches(tpe) =>
        q"$mods val $name = $ReactMetadataCompanionSym.empty()"

      case x@Select(_, _) if scalaXmlNull.matches(x) =>
        q"""
            $ReactMetadataCompanionSym.empty()
          """

      case q"new $tpe($prefixLit, $labelTree, $metadataIdent, $scope, $minimizeEmpty, ..$children)" if hasElemType.matches(tpe) =>
        mkReactDOM(labelTree, transform(metadataIdent), children.map(transform))

      case q"new $tpe($label, $node, ${md: TermName})" if hasAttributeType.matches(tpe) =>
        val value = node match {
          case Apply(Select(New(hasTextType(_)), _), List(x)) => x
          case x@hasStringType(_) => x
          case x => x
        }

        q"""
            $ReactMetadataCompanionSym.apply($label, $value, $md)
          """

      case q"new $tpe($lit)" if hasTextType.matches(tpe) => lit match {
        case Literal(Constant(const: String)) if isWhitespace(const) => q"""null"""
        case x => x
      }

      case x@TypeTree() if x.tpe != null && x.tpe =:= typeOf[Elem] =>
        TypeTree(typeOf[ReactDOM])

      case Ident(hasElemType(_)) => q"$ReactDOMTpe"

      case _ => super.transform(tree)
    }
  }

  def mkReactDOM(labelTree: Tree, metadata: Tree, children: List[Tree]): Tree = labelTree match {
    case Literal(Constant(label: String)) =>
      q"""
        $ReactDOMCompanionSym.selectDynamic($labelTree, $metadata, ..$children)
      """

    case x =>
      c.error(x.pos, s"Can't make ReactDOM by ${showRaw(x)}")
      x
  }
}
