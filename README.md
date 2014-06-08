scala-js-react
============

Scala-js-react provides nice statically typed interface to [Facebook's React](http://facebook.github.io/react/).

# Features

`scala-js-react` aims to support all [Facebook React](http://facebook.github.io/react/) features.

## `scalax` annotation

While [Facebook React](http://facebook.github.io/react/) uses JSX, `scala-js-react` offers `scalax` support. 
Basically `scalax` allows to use Scala xml literals to build React DOM.

`scalax` uses macro annototations, so it requires macro paradise compiler plugin.

```
object HelloMessage extends TypedReactSpec {

  case class State()
  case class Props(name: String)

  def getInitialState(self: This) = State()

  @scalax
  def render(self: This) = {
    <div>Hello {self.props.name}</div>
  }
}

React.renderComponent(
  HelloMessage(HelloMessage.Props(name = "John")),
  mountNode
)
```

## Internal State

A component can maintain internal state

```
object Timer extends TypedReactSpec with TypedEventListeners {

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
```

## Typed listeners and closures

```
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
```

# How to run examples

    $ sbt "project scalajs-react-examples" fastOptJS
    $ open scalajs-react-examples/index.html

# Copyright

Copyright © 2014 Xored Software Inc
