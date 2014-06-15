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

  def renderComponentToString[State, Props](component: ReactComponent[State, Props]): String = ???

  def DOM: js.Dynamic = ???
}
