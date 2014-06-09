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
