package org.scalajs.react

import scala.scalajs.js

trait ThisLike {
  type Props
  type State

  def props: Props
  def state: State
  def refs(key: String): ReactDOMRef

  def setState(newState: State): Unit
  def setState(newState: State, callback: () => Unit): Unit
}

object ThisLike {
  val REACT_KEY = "key"
  val REACT_REF = "ref"
}

trait JsBridgeLike[This <: ThisLike] {
  def mkReactProps(props: This#Props): js.Dictionary[js.Any]
  def mkReactState(state: This#State): js.Dictionary[js.Any]

  def dynamicAsThis(dynamic: js.Dynamic): This
  def dynamicAsProps(dynamic: js.Dynamic): This#Props
  def dynamicAsState(dynamic: js.Dynamic): This#State
}
