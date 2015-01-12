package com.xored.scalajs.react.examples

import org.scalajs.dom._
import com.xored.scalajs.react._
import scala.scalajs.js.annotation.JSExport
import com.xored.scalajs.react.examples.todomvc.TodoApp
import com.xored.scalajs.react.examples.timer.Timer
import com.xored.scalajs.react.examples.hello.HelloMessage
import com.xored.scalajs.react.examples.say.Say
import com.xored.scalajs.react.examples.children.Children
import com.xored.scalajs.react.examples.export.Export

@JSExport("App")
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
  def export(parent: HTMLElement) = {
    React.renderComponent(
      Export(Export.Props()),
      parent
    )
  }

  @JSExport
  def children(parent: HTMLElement) = {
    Children.main(parent)
  }

}
