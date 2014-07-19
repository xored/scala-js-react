package com.xored.scalajs.react.comp

import com.xored.scalajs.react._

object Panel extends TypedReactSpec {
  case class State()
  case class Props(children: ReactDOM*)

  def getInitialState(self: This) = State()

  @scalax
  def render(self: This) = {
    <div className="panel">
      {
        self.props.children
      }
    </div>
  }
}
