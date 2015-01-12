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

package com.xored.scalajs.react.interop

import scala.scalajs.js
import com.xored.scalajs.react.ReactDOM

trait ReactComponentClass[State, Props] extends js.Function1[js.Dictionary[Any], ReactComponent[State, Props]]

trait ReactComponent[State, Props] extends ReactDOM {
  def setState(state: State): Unit = js.native
}
