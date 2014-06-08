package org.scalajs.react.internal

import scala.scalajs.js
import org.scalajs.react.ReactDOM

class ReactDOMBuffer extends scala.collection.mutable.ArrayBuffer[ReactDOM] {

  private val EMPTY_STRING = js.Any.fromString("")

  def &+(o: Any): ReactDOMBuffer = {
    o match {
      case null | _: Unit | EMPTY_STRING => // ignore

      // just js.Object
      case n if n.getClass == null => super.+=(n.asInstanceOf[ReactDOM])

      // in ScalaJS Long is iterable
      case x: scala.Long => super.+=(x.toString.asInstanceOf[ReactDOM])

      case it: Iterator[_] => it foreach &+
      case ns: Iterable[_] => this &+ ns.iterator
      case ns: Array[_] => this &+ ns.iterator
      case n => super.+=(n.asInstanceOf[ReactDOM])
    }

    this
  }

}
