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
