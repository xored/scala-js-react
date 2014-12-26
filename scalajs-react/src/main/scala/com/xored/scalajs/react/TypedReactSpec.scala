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
import com.xored.scalajs.react.util.UUID

object TypedThisLike {
  val STATE_KEY = "__scalajs_react_state"
  val PROPS_KEY = "__scalajs_react_props"
  val COMPONENT_ID_KEY = "__scalajs_react_component_id"

  def reactPropsBridge[T] = new JSBridge[T] {
    def apply(props: T) = {
      js.Dictionary(
        (PROPS_KEY, props.asInstanceOf[js.Any]),
        (COMPONENT_ID_KEY, UUID())
      )
    }

    def apply(value: js.Any) = {
      val dynamic = value.asInstanceOf[js.Dynamic]
      dynamic.selectDynamic(PROPS_KEY).asInstanceOf[T]
    }
  }

  def reactStateBridge[T] = new JSBridge[T] {
    def apply(state: T): js.Any = {
      js.Dictionary(
        (STATE_KEY, state.asInstanceOf[js.Any])
      )
    }

    def apply(value: js.Any) = {
      val dynamic = value.asInstanceOf[js.Dynamic]
      dynamic.selectDynamic(STATE_KEY).asInstanceOf[T]
    }
  }
}

class TypedThisLike(self: js.Any) extends ThisLike {

  import TypedThisLike._

  private val dynamic = self.asInstanceOf[js.Dynamic]

  def props = dynamic.props.selectDynamic(PROPS_KEY).asInstanceOf[Props]

  def state = dynamic.state.selectDynamic(STATE_KEY).asInstanceOf[State]

  def componentId = dynamic.props.selectDynamic(COMPONENT_ID_KEY).toString

  def refs(key: String) = dynamic.refs.selectDynamic(key).asInstanceOf[ReactDOMRef]

  def isMounted: Boolean = dynamic.isMounted().asInstanceOf[Boolean]

  def setState(newState: State) {
    setState(newState, () => {})
  }

  def setState(newState: State, callback: () => Unit) {
    val dict = js.Dictionary((STATE_KEY, newState))
    dynamic.setState(dict, js.Any.fromFunction0(callback))
  }

  def forceUpdate(): Unit = {
    dynamic.forceUpdate()
  }
}

trait TypedReactSpec extends ReactSpec {
  spec =>

  type State
  type Props

  implicit final val reactStateBridge = TypedThisLike.reactStateBridge[State]
  implicit final val reactPropsBridge = TypedThisLike.reactPropsBridge[Props]

  implicit final val thisReader = new JSBridgeFrom[This] {
    def apply(value: js.Any) = {
      val dynamic = value.asInstanceOf[js.Dynamic]
      new TypedThisLike(dynamic) {
        type State = spec.type#State
        type Props = spec.type#Props
      }
    }
  }

  final type This = TypedThisLike {
    type State = spec.type#State
    type Props = spec.type#Props
  }
}
