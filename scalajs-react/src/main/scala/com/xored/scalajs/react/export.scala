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

import scala.scalajs.js
import com.xored.scalajs.react.export._

object export extends ExportInstances with JSBridgeInstances {
  /**
   * Structure to describe export of value/method to react spec
   *
   * @param name name of property in react spec
   * @param value value
   */
  case class Export(name: String, value: js.Any)
}

trait JSBridgeFrom[T] {
  def apply(value: js.Any): T
}

trait JSBridgeTo[T] {
  def apply(value: T): js.Any
}

trait JSBridge[T] extends JSBridgeFrom[T] with JSBridgeTo[T]

/**
 * Instances to export functions with different arity
 */
trait ExportInstances {
  def export1[T, R](name: String, f: T => R)(implicit m: JSBridgeFrom[T], r: JSBridgeTo[R]): Export = {
    Export(name, js.ThisFunction.fromFunction1[js.Any, js.Any](self => r(f(m(self)))))
  }

  def export2[T, A, R](name: String, f: (T, A) => R)(implicit m: JSBridgeFrom[T], r: JSBridgeTo[R], ma: JSBridgeFrom[A]): Export = {
    Export(name, js.ThisFunction.fromFunction2[js.Any, js.Any, js.Any]((self, a) => r(f(m(self), ma(a)))))
  }

  def export3[T, A, B, R](name: String, f: (T, A, B) => R)(implicit m: JSBridgeFrom[T], r: JSBridgeTo[R], ma: JSBridgeFrom[A], mb: JSBridgeFrom[B]): Export = {
    Export(name, js.ThisFunction.fromFunction3[js.Any, js.Any, js.Any, js.Any]((self, a, b) => r(f(m(self), ma(a), mb(b)))))
  }
}

object ExportInstances extends ExportInstances

/**
 * Default bridges
 */
trait JSBridgeInstances {
  implicit def reactAnyWriter[T <: js.Any]: JSBridgeTo[T] = new JSBridgeTo[T] {
    def apply(value: T) = value
  }

  implicit def reactUnitWriter: JSBridgeTo[Unit] = new JSBridgeTo[Unit] {
    def apply(value: Unit) = js.undefined
  }

  implicit def reactBooleanExport: JSBridgeTo[Boolean] = new JSBridgeTo[Boolean] {
    def apply(value: Boolean): js.Any = value
  }
}

object JSBridgeInstances extends JSBridgeInstances
