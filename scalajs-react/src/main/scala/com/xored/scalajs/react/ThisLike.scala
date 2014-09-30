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

trait ThisLike {
  type Props
  type State

  def props: Props
  def state: State
  def refs(key: String): ReactDOMRef

  def setState(newState: State): Unit
  def setState(newState: State, callback: () => Unit): Unit

  def forceUpdate(): Unit
}

object ThisLike {
  val REACT_KEY = "key"
  val REACT_REF = "ref"
}
