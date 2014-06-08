package org.scalajs.react.interop

import scala.scalajs.js
import org.scalajs.react.ReactDOM

trait ReactComponentClass[State, Props] extends js.Function1[js.Object, ReactComponent[State, Props]]

trait ReactComponent[State, Props] extends ReactDOM {
  def setState(state: State): Unit = ???
}

