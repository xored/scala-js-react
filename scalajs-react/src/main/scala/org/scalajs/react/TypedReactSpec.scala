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

package org.scalajs.react

import scala.scalajs.js
import org.scalajs.react.util.UUID

object TypedThisLike {
  val STATE_KEY = "__scalajs_react_state"
  val PROPS_KEY = "__scalajs_react_props"
  val COMPONENT_ID_KEY = "__scalajs_react_component_id"
}

class TypedThisLike(val dynamic: js.Dynamic) extends ThisLike {

  import TypedThisLike._

  def props = dynamic.props.selectDynamic(PROPS_KEY).asInstanceOf[Props]

  def state = dynamic.state.selectDynamic(STATE_KEY).asInstanceOf[State]

  def componentId = dynamic.state.selectDynamic(COMPONENT_ID_KEY).toString

  def refs(key: String) = dynamic.refs.selectDynamic(key).asInstanceOf[ReactDOMRef]

  def setState(newState: State) {
    setState(newState, () => {})
  }

  def setState(newState: State, callback: () => Unit) {
    val dict = js.Dictionary((STATE_KEY, newState))
    dynamic.setState(dict, js.Any.fromFunction0(callback))
  }
}

trait TypedReactSpec extends ReactSpec {
  spec =>

  type State
  type Props

  val jsBridge: JsBridgeLike[This] = new TypedJsBridge()

  type This = TypedThisLike {
    type State = spec.type#State
    type Props = spec.type#Props
  }

}

class TypedJsBridge[T <: TypedThisLike] extends JsBridgeLike[T] {
  import TypedThisLike._

  def dynamicAsState(dynamic: js.Dynamic) = {
    dynamic.selectDynamic(STATE_KEY).asInstanceOf[T#State]
  }

  def dynamicAsProps(dynamic: js.Dynamic) = {
    dynamic.selectDynamic(PROPS_KEY).asInstanceOf[T#Props]
  }

  def dynamicAsThis(dynamic: js.Dynamic): T = {
    // TODO this doesn't work if T isn't TypedThisLike
    new TypedThisLike(dynamic).asInstanceOf[T]
  }

  def mkReactState(state: T#State) = {
    js.Dictionary(
      (STATE_KEY, state.asInstanceOf[js.Any]),
      (COMPONENT_ID_KEY, UUID())
    )
  }

  def mkReactProps(props: T#Props) = {
    js.Dictionary(
      (PROPS_KEY, props.asInstanceOf[js.Any]),
      (COMPONENT_ID_KEY, UUID())
    )
  }
}