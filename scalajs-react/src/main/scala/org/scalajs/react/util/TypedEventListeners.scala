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

package org.scalajs.react.util

import scala.scalajs.js
import org.scalajs.dom._

class TypedEvent[+T <: EventTarget] extends Event {
  override val target: T = ???
}

trait TypedEventListeners {

  // TODO extend this list

  object element {
    def onClick(e: TypedEvent[HTMLElement] => Unit) = js.Any.fromFunction1(e)

    def onScroll(e: TypedEvent[HTMLElement] => Unit) = js.Any.fromFunction1(e)

    def onEvent(e: TypedEvent[HTMLElement] => Unit) = js.Any.fromFunction1(e)

    def onBlur(e: TypedEvent[HTMLElement] => Unit) = js.Any.fromFunction1(e)

    def onKeyPress(e: KeyboardEvent with TypedEvent[HTMLElement] => Unit) = js.Any.fromFunction1(e)
  }

  object input {
    def onChange(e: TypedEvent[HTMLInputElement] => Unit) = js.Any.fromFunction1(e)

    def onBlur(e: TypedEvent[HTMLInputElement] => Unit) = js.Any.fromFunction1(e)

    def onInput(e: TypedEvent[HTMLInputElement] => Unit) = js.Any.fromFunction1(e)

    def onKeyPress(e: KeyboardEvent with TypedEvent[HTMLInputElement] => Unit) = js.Any.fromFunction1(e)
  }

  object button {
    def onClick(e: TypedEvent[HTMLInputElement] => Unit) = js.Any.fromFunction1(e)
  }

  object form {
    def onSubmit(e: TypedEvent[HTMLElement] => Unit) = js.Any.fromFunction1(e)
  }

}

object TypedEventListeners extends TypedEventListeners
