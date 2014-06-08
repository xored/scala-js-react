package org.scalajs.react

import scala.scalajs.js

import scala.language.experimental.macros
import org.scalajs.react.interop._

trait ReactSpec {
  type This <: ThisLike
  type ComponentType = ReactComponent[This#State, This#Props]

  private[this] lazy val reactClass = React.createClass(this)

  val jsBridge: JsBridgeLike[This]

  def apply[T](props: This#Props, ref: js.Any = js.Any.fromUnit()): ComponentType = {
    val dict = jsBridge.mkReactProps(props)

    if (!key(props).isInstanceOf[js.Undefined]) dict.update(ThisLike.REACT_KEY, key(props))
    if (!ref.isInstanceOf[js.Undefined]) dict.update(ThisLike.REACT_REF, ref)

    reactClass(dict)
  }

  def key(props: This#Props): js.Any = {
    js.Any.fromUnit()
  }

  def render(self: This): ReactDOM

  def componentDidMount(self: This) {}

  def componentDidUpdate(self: This, prevProps: This#Props, prevState: This#State) {}

  def componentWillMount(self: This) {}

  def componentWillUnmount(self: This) {}

  def componentWillReceiveProps(self: This, nextProps: This#Props) {}

  def shouldComponentUpdate(self: This, nextProps: This#Props, nextState: This#State) = {
    self.props != nextProps || self.state != nextState
  }

  def getInitialState(self: This): This#State

}


