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
  val bubbles: Boolean
  val cancelable: Boolean

  val currentTarget: dom.EventTarget
  val target: dom.EventTarget
  val nativeEvent: dom.Event

  def preventDefault(): Unit
  def stopPropagation(): Unit

  val defaultPrevented: Boolean
  val eventPhase: Int
  val isTrusted: Boolean
  val timeStamp: js.Date
  val `type`: String
}

trait ClipboardEvent extends SyntheticEvent {
  val clipboardData: dom.DataTransfer
}

trait KeyboardEvent extends SyntheticEvent {
  val altKey: Boolean
  val ctrlKey: Boolean
  val metaKey: Boolean
  val shiftKey: Boolean

  val charCode: Int
  val key: String
  val keyCode: Int
  val locale: String
  val location: Int
  val repeat: Boolean
  val which: Int

  def getModifierState(keyArg: String): Boolean
}

trait FocusEvent extends SyntheticEvent {
  val relatedTarget: dom.EventTarget
}

trait FormEvent extends SyntheticEvent

trait MouseEvent extends SyntheticEvent {
  val altKey: Boolean
  val ctrlKey: Boolean
  val metaKey: Boolean
  val shiftKey: Boolean

  val button: Int
  val buttons: Int

  val clientX: Int
  val clientY: Int
  val pageX: Int
  val pageY: Int
  val screenX: Int
  val screenY: Int

  val relatedTarget: dom.EventTarget
}

trait TouchEvent extends SyntheticEvent {
  val altKey: Boolean
  val ctrlKey: Boolean
  val metaKey: Boolean
  val shiftKey: Boolean

  val changedTouches: dom.TouchList
  val targetTouches: dom.TouchList
  val touches: dom.TouchList

  def getModifierState(keyArg: String): Boolean
}

trait UIEvent extends SyntheticEvent {
  val detail: Int
  val view: dom.Window
}

trait WheelEvent extends SyntheticEvent {
  val deltaMode: Int
  val deltaX: Double
  val deltaY: Double
  val deltaZ: Double
}

