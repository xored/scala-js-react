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

package com.xored.scalajs.react

import scala.scalajs.js

import scala.language.experimental.macros
import com.xored.scalajs.react.interop._

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


