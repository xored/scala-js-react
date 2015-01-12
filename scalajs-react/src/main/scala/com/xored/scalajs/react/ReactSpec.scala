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
import com.xored.scalajs.react.export._

trait ReactSpec {
  type This <: ThisLike
  type ComponentType = ReactComponent[This#State, This#Props]

  private[this] lazy val reactClass = React.createClass(this)

  def exports(): List[Export] = List(
    export1("render", render),
    export1("componentDidMount", componentDidMount),
    export1("componentWillMount", componentWillMount),
    export1("componentWillUnmount", componentWillUnmount),
    export2("componentWillReceiveProps", componentWillReceiveProps),
    export3("componentDidUpdate", componentDidUpdate),
    export3("shouldComponentUpdate", shouldComponentUpdate),
    export1("getInitialState", getInitialState)
  )

  implicit def thisReader: JSBridgeFrom[This]

  implicit def reactPropsBridge: JSBridge[This#Props]

  implicit def reactStateBridge: JSBridge[This#State]

  def apply[T](props: This#Props, ref: Any = ())(implicit e: JSBridgeTo[This#Props]): ComponentType = {
    val dict = e(props).asInstanceOf[js.Dictionary[Any]] // anything js.Any could be cast to js.Dictionary

    js.UndefOr.any2undefOrA(key(props)).foreach(
      value => dict.update(ThisLike.REACT_KEY, value))

    js.UndefOr.any2undefOrA(ref).foreach(
      value => dict.update(ThisLike.REACT_REF, value))

    reactClass(dict)
  }

  def key(props: This#Props): Any = ()

  def render(self: This): ReactDOM

  def componentDidMount(self: This): Unit = {}

  def componentDidUpdate(self: This, prevProps: This#Props, prevState: This#State): Unit = {}

  def componentWillMount(self: This): Unit = {}

  def componentWillUnmount(self: This): Unit = {}

  def componentWillReceiveProps(self: This, nextProps: This#Props): Unit = {}

  def shouldComponentUpdate(self: This, nextProps: This#Props, nextState: This#State): Boolean = {
    self.props != nextProps || self.state != nextState
  }

  def getInitialState(self: This): This#State
}

