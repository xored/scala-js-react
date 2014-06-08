package org.scalajs.react

import scala.scalajs.js
import org.scalajs.dom._
import org.scalajs.react.interop.ReactJS

trait ReactDOM extends HTMLElement

trait ReactDOMRef extends js.Object {
  def getDOMNode[T <: HTMLElement](): T = ??? // we need brackets for interop!
}

object ReactDOM {
  def selectDynamic(name: String, props: Any, args: js.Any*): ReactDOM = {
    js.Dynamic.global.React.DOM.selectDynamic(name).apply(props.asInstanceOf[js.Any] +: args: _*).asInstanceOf[ReactDOM]
  }
}
