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

import scala.xml.Elem
import scala.language.experimental.macros
import scala.annotation.StaticAnnotation

import org.scalajs.react.internal.ScalaxImpl

object scalax {
  def apply(elem: Elem): ReactDOM = macro ScalaxImpl.apply
}

class scalax(verbose: Boolean = false) extends StaticAnnotation {
  def macroTransform(annottees: Any*) = macro ScalaxImpl.macroTransform
}
