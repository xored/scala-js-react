package org.scalajs.react.examples.timer

import org.scalajs.react._
import org.scalajs.dom._

object Timer extends TypedReactSpec {

  case class Props()
  case class State(secondsElapsed: Int, interval: Option[Int])

  def getInitialState(self: This) = State(secondsElapsed = 0, interval = None)

  implicit class Closure(self: This) {
    import self._

    val tick = () => {
      setState(state.copy(secondsElapsed = state.secondsElapsed + 1))
    }
  }

  override def componentDidMount(self: This) = {
    val interval = window.setInterval(self.tick, 1000)

    self.setState(self.state.copy(interval = Some(interval)))
  }

  override def componentWillUnmount(self: This) = {
    self.state.interval.foreach(window.clearInterval)
  }

  @scalax
  def render(self: This) = {
    <div>Seconds Elapsed: {self.state.secondsElapsed}</div>
  }
}
