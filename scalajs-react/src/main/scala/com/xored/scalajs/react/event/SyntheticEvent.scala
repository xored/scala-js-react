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

package com.xored.scalajs.react.event

import org.scalajs.dom

import scala.scalajs.js

trait SyntheticEvent extends js.Object {
  val bubbles: Boolean = js.native
  val cancelable: Boolean = js.native

  val currentTarget: dom.EventTarget = js.native
  val target: dom.EventTarget = js.native
  val nativeEvent: dom.Event = js.native

  def preventDefault(): Unit = js.native
  def stopPropagation(): Unit = js.native

  val defaultPrevented: Boolean = js.native
  val eventPhase: Int = js.native
  val isTrusted: Boolean = js.native
  val timeStamp: js.Date = js.native
  val `type`: String = js.native
}

trait ClipboardEvent extends SyntheticEvent {
  val clipboardData: dom.DataTransfer = js.native
}

trait KeyboardEvent extends SyntheticEvent {
  val altKey: Boolean = js.native
  val ctrlKey: Boolean = js.native
  val metaKey: Boolean = js.native
  val shiftKey: Boolean = js.native

  val charCode: Int = js.native
  val key: String = js.native
  val keyCode: Int = js.native
  val locale: String = js.native
  val location: Int = js.native
  val repeat: Boolean = js.native
  val which: Int = js.native

  def getModifierState(keyArg: String): Boolean = js.native
}

trait FocusEvent extends SyntheticEvent {
  val relatedTarget: dom.EventTarget = js.native
}

trait FormEvent extends SyntheticEvent

trait MouseEvent extends SyntheticEvent {
  val altKey: Boolean = js.native
  val ctrlKey: Boolean = js.native
  val metaKey: Boolean = js.native
  val shiftKey: Boolean = js.native

  val button: Int = js.native
  val buttons: Int = js.native

  val clientX: Int = js.native
  val clientY: Int = js.native
  val pageX: Int = js.native
  val pageY: Int = js.native
  val screenX: Int = js.native
  val screenY: Int = js.native

  val relatedTarget: dom.EventTarget = js.native
}

trait TouchEvent extends SyntheticEvent {
  val altKey: Boolean = js.native
  val ctrlKey: Boolean = js.native
  val metaKey: Boolean = js.native
  val shiftKey: Boolean = js.native

  val changedTouches: dom.TouchList = js.native
  val targetTouches: dom.TouchList = js.native
  val touches: dom.TouchList = js.native

  def getModifierState(keyArg: String): Boolean = js.native
}

trait UIEvent extends SyntheticEvent {
  val detail: Int = js.native
  val view: dom.Window = js.native
}

trait WheelEvent extends SyntheticEvent {
  val deltaMode: Int = js.native
  val deltaX: Double = js.native
  val deltaY: Double = js.native
  val deltaZ: Double = js.native
}

