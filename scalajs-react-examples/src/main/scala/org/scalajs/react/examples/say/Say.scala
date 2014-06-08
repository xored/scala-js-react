package org.scalajs.react.examples.say

import org.scalajs.react._
import org.scalajs.dom._
import org.scalajs.react.util.TypedEventListeners

object Say extends TypedReactSpec with TypedEventListeners {

  case class Props()
  case class State(text: String)

  implicit class Closure(self: This) {
    import self._

    val onChange = input.onChange(e => {
      setState(state.copy(text = e.target.value))
    })

    val onClick = button.onClick(e => {
      alert(state.text)
    })
  }

  def getInitialState(self: This) = State(text = "")

  @scalax
  def render(self: This) = {
    <div>
      <input type="text" value={self.state.text} onChange={self.onChange}></input>
      <button type="text" onClick={self.onClick}>Say</button>
    </div>
  }
}
