package org.scalajs.react.examples

import org.scalajs.dom._
import org.scalajs.react._
import scala.scalajs.js.annotation.JSExport
import org.scalajs.react.examples.todomvc.TodoApp
import org.scalajs.react.examples.timer.Timer
import org.scalajs.react.examples.hello.HelloMessage
import org.scalajs.react.examples.say.Say
import org.scalajs.react.examples.children.Children

@JSExport
object App {

  @JSExport
  def todoApp(parent: HTMLElement) = {
    React.renderComponent(
      TodoApp(TodoApp.Props()),
      parent
    )
  }

  @JSExport
  def timer(parent: HTMLElement) = {
    React.renderComponent(
      Timer(Timer.Props()),
      parent
    )
  }

  @JSExport
  def hello(parent: HTMLElement) = {
    React.renderComponent(
      HelloMessage(HelloMessage.Props("Jack")),
      parent
    )
  }

  @JSExport
  def say(parent: HTMLElement) = {
    React.renderComponent(
      Say(Say.Props()),
      parent
    )
  }

  @JSExport
  def children(parent: HTMLElement) = {
    Children.main(parent)
  }

}
