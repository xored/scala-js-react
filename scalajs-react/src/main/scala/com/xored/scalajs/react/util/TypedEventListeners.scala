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

package com.xored.scalajs.react.util

import com.xored.scalajs.react.event._

import scala.scalajs.js
import org.scalajs.dom._

trait TypedEventListeners {

  trait ElementEventListeners[T <: EventTarget] {
    def onEvent(e: TypedSyntheticEvent[T] => Unit): js.Function1[TypedSyntheticEvent[T], Unit] = js.Any.fromFunction1(e)

    def onCopy(e: TypedClipboardEvent[T] => Unit): js.Function1[TypedClipboardEvent[T], Unit] = js.Any.fromFunction1(e)
    def onCut(e: TypedClipboardEvent[T] => Unit): js.Function1[TypedClipboardEvent[T], Unit] = js.Any.fromFunction1(e)
    def onPaste(e: TypedClipboardEvent[T] => Unit): js.Function1[TypedClipboardEvent[T], Unit] = js.Any.fromFunction1(e)

    def onKeyDown(e: TypedKeyboardEvent[T] => Unit): js.Function1[TypedKeyboardEvent[T], Unit] = js.Any.fromFunction1(e)
    def onKeyPress(e: TypedKeyboardEvent[T] => Unit): js.Function1[TypedKeyboardEvent[T], Unit] = js.Any.fromFunction1(e)
    def onKeyUp(e: TypedKeyboardEvent[T] => Unit): js.Function1[TypedKeyboardEvent[T], Unit] = js.Any.fromFunction1(e)

    def onFocus(e: TypedFocusEvent[T] => Unit): js.Function1[TypedFocusEvent[T], Unit] = js.Any.fromFunction1(e)
    def onBlur(e: TypedFocusEvent[T] => Unit): js.Function1[TypedFocusEvent[T], Unit] = js.Any.fromFunction1(e)

    def onClick(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onDoubleClick(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onDrag(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onDragEnd(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onDragEnter(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onDragExit(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onDragLeave(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onDragOver(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onDragStart(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onDrop(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onMouseDown(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onMouseEnter(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onMouseLeave(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onMouseMove(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onMouseOut(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onMouseOver(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)
    def onMouseUp(e: TypedMouseEvent[T] => Unit): js.Function1[TypedMouseEvent[T], Unit] = js.Any.fromFunction1(e)

    def onTouchCancel(e: TypedTouchEvent[T] => Unit): js.Function1[TypedTouchEvent[T], Unit] = js.Any.fromFunction1(e)
    def onTouchEnd(e: TypedTouchEvent[T] => Unit): js.Function1[TypedTouchEvent[T], Unit] = js.Any.fromFunction1(e)
    def onTouchMove(e: TypedTouchEvent[T] => Unit): js.Function1[TypedTouchEvent[T], Unit] = js.Any.fromFunction1(e)
    def onTouchStart(e: TypedTouchEvent[T] => Unit): js.Function1[TypedTouchEvent[T], Unit] = js.Any.fromFunction1(e)

    def onScroll(e: TypedUIEvent[T] => Unit): js.Function1[TypedUIEvent[T], Unit] = js.Any.fromFunction1(e)

    def onWheel(e: TypedWheelEvent[T] => Unit): js.Function1[TypedWheelEvent[T], Unit] = js.Any.fromFunction1(e)
  }

  object element extends ElementEventListeners[HTMLElement]

  object input extends ElementEventListeners[HTMLInputElement] {
    def onChange(e: TypedInputFormEvent => Unit): js.Function1[TypedInputFormEvent, Unit] = js.Any.fromFunction1(e)
    def onInput(e: TypedInputFormEvent => Unit): js.Function1[TypedInputFormEvent, Unit] = js.Any.fromFunction1(e)
    def onSubmit(e: TypedInputFormEvent => Unit): js.Function1[TypedInputFormEvent, Unit] = js.Any.fromFunction1(e)
  }

  object textarea extends ElementEventListeners[HTMLInputElement] {
    def onChange(e: TypedInputFormEvent => Unit): js.Function1[TypedInputFormEvent, Unit] = js.Any.fromFunction1(e)
    def onInput(e: TypedInputFormEvent => Unit): js.Function1[TypedInputFormEvent, Unit] = js.Any.fromFunction1(e)
    def onSubmit(e: TypedInputFormEvent => Unit): js.Function1[TypedInputFormEvent, Unit] = js.Any.fromFunction1(e)
  }

  object checkbox extends ElementEventListeners[HTMLInputElement] {
    def onChange(e: TypedCheckboxFormEvent => Unit): js.Function1[TypedCheckboxFormEvent, Unit] = js.Any.fromFunction1(e)
    def onInput(e: TypedCheckboxFormEvent => Unit): js.Function1[TypedCheckboxFormEvent, Unit] = js.Any.fromFunction1(e)
    def onSubmit(e: TypedCheckboxFormEvent => Unit): js.Function1[TypedCheckboxFormEvent, Unit] = js.Any.fromFunction1(e)
  }

  object option extends ElementEventListeners[HTMLInputElement] {
    def onChange(e: TypedOptionFormEvent => Unit): js.Function1[TypedOptionFormEvent, Unit] = js.Any.fromFunction1(e)
    def onInput(e: TypedOptionFormEvent => Unit): js.Function1[TypedOptionFormEvent, Unit] = js.Any.fromFunction1(e)
    def onSubmit(e: TypedOptionFormEvent => Unit): js.Function1[TypedOptionFormEvent, Unit] = js.Any.fromFunction1(e)
  }

  object form {
    def onChange(e: TypedFormEvent[HTMLFormElement] => Unit): js.Function1[TypedFormEvent[HTMLFormElement], Unit] = js.Any.fromFunction1(e)
    def onInput(e: TypedFormEvent[HTMLFormElement] => Unit): js.Function1[TypedFormEvent[HTMLFormElement], Unit] = js.Any.fromFunction1(e)
    def onSubmit(e: TypedFormEvent[HTMLFormElement] => Unit): js.Function1[TypedFormEvent[HTMLFormElement], Unit] = js.Any.fromFunction1(e)
  }

  object button extends ElementEventListeners[HTMLInputElement]

}

object TypedEventListeners extends TypedEventListeners
