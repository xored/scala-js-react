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

import com.xored.scalajs.react.interop._
import org.scalajs.dom._
import scala.scalajs.js

object React {

  val DOM = ReactDOM

  def renderComponent[C <: ReactDOM](dom: C, container: HTMLElement): C = {
    ReactJS.renderComponent(dom, container)
  }

  def renderComponentToString(dom: ReactDOM): String = {
    ReactJS.renderComponentToString(dom)
  }

  def renderComponentToStaticMarkup(dom: ReactDOM): String = {
    ReactJS.renderComponentToStaticMarkup(dom)
  }

  def createClass(spec: ReactSpec): ReactComponentClass[spec.type#This#State, spec.type#This#Props] = {
    val exports = spec.exports()
    val reactSpec = js.Dictionary(exports.map(x => (x.name, x.value)): _*)

    ReactJS.createClass(reactSpec)
  }

}