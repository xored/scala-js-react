package org.scalajs.react.interop

import scala.scalajs.js
import js.annotation.JSName
import org.scalajs.dom

/**
 * Interop with react.js
 */
@JSName("React")
object ReactJS extends js.Object {
  def createClass[State, Props](spec: js.Dictionary[js.Any]): ReactComponentClass[State, Props] = ???

  def renderComponent[State, Props](component: ReactComponent[State, Props], container: dom.HTMLElement): ReactComponent[State, Props] = ???

  def DOM: js.Dynamic = ???
}
