package org.scalajs.react

import scala.xml.Elem
import scala.language.experimental.macros
import scala.annotation.StaticAnnotation

import org.scalajs.react.internal.ScalaxImpl

object scalax {
  def apply(elem: Elem): ReactDOM = macro ScalaxImpl.apply
}

class scalax(verbose: Boolean = false) extends StaticAnnotation {
  def macroTransform(annottees: Any*) = macro ScalaxImpl.macroTransform
}
