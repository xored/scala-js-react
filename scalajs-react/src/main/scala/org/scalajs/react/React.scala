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

import org.scalajs.react.interop._
import org.scalajs.react.util._
import org.scalajs.dom._
import scala.scalajs.js

object React {

  val DOM = ReactDOM

  def renderComponent[S, P](component: ReactComponent[S, P], container: HTMLElement): ReactComponent[S, P] = {
    ReactJS.renderComponent(component, container)
  }

  def createClass(spec: ReactSpec): ReactComponentClass[spec.type#This#State, spec.type#This#Props] = {
    import js.ThisFunction._

    type This = spec.type#This

    val reactSpec = js.Dictionary.empty[js.Any]
    val bridge = spec.jsBridge

    def renderFn[R](f: This => ReactDOM) = {
      fromFunction1[js.Dynamic, ReactDOM](x => f(bridge.dynamicAsThis(x)))
    }

    def thisFn[R](f: This => Unit) = {
      fromFunction1[js.Dynamic, Unit](x => f(bridge.dynamicAsThis(x)))
    }

    def didUpdateFn(f: (This, This#Props, This#State) => Unit) = {
      fromFunction3[js.Dynamic, js.Dynamic, js.Dynamic, Unit]((self, dynPrevProps, dynPrevState) => {
        val prevProps = bridge.dynamicAsProps(dynPrevProps)
        val prevState = bridge.dynamicAsState(dynPrevState)

        f(bridge.dynamicAsThis(self), prevProps, prevState)
      })
    }

    def shouldComponentUpdateFn(f: (This, This#Props, This#State) => Boolean) = {
      fromFunction3[js.Dynamic, js.Dynamic, js.Dynamic, js.Boolean]((self, dynPrevProps, dynPrevState) => {
        val prevProps = bridge.dynamicAsProps(dynPrevProps)
        val prevState = bridge.dynamicAsState(dynPrevState)

        f(bridge.dynamicAsThis(self), prevProps, prevState)
      })
    }

    def propsFn(f: (This, This#Props) => Unit) = {
      fromFunction2[js.Dynamic, js.Dynamic, Unit]((self, nextProps) => {
        f(bridge.dynamicAsThis(self), bridge.dynamicAsProps(nextProps))
      })
    }

    def stateFn(f: This => This#State) = {
      fromFunction1[js.Dynamic, js.Dictionary[js.Any]](self => {
        bridge.mkReactState(f(bridge.dynamicAsThis(self)))
      })
    }

    reactSpec += "render" -> renderFn(spec.render)
    reactSpec += "componentDidMount" -> thisFn(spec.componentDidMount)
    reactSpec += "componentDidUpdate" -> didUpdateFn(spec.componentDidUpdate)
    reactSpec += "componentWillMount" -> thisFn(spec.componentWillMount)
    reactSpec += "componentWillUnmount" -> thisFn(spec.componentWillUnmount)
    reactSpec += "shouldComponentUpdate" -> shouldComponentUpdateFn(spec.shouldComponentUpdate)
    reactSpec += "componentWillReceiveProps" -> propsFn(spec.componentWillReceiveProps)
    reactSpec += "getInitialState" -> stateFn(self => spec.getInitialState(self))

    ReactJS.createClass(reactSpec)
  }

}