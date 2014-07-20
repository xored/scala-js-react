package com.xored.scalajs.react.examples.export

import com.xored.scalajs.react._
import com.xored.scalajs.react.export._
import com.xored.scalajs.react.util.TypedEventListeners
import scala.scalajs.js

object ContentEditable extends TypedReactSpec {
  case class Props()
  case class State()

  def getInitialState(self: This) = State()

  override def exports() = export1("focus", focus) :: super.exports()

  def focus(self: This): Unit = {
    self.refs("input").getDOMNode().focus()
  }

  @scalax
  def render(self: This) = {
    val style = Map("width" -> "100px", "background" -> "#efef00", "float" -> "left")
    <div style={style} contentEditable={true} ref="input"></div>
  }
}

trait Focusable extends js.Object {
  def focus(): Unit
}

object Focusable {
  def cast(ref: ReactDOMRef): Option[Focusable] = {
    if (ref.hasOwnProperty("focus")) Some(ref.asInstanceOf[Focusable])
    else None
  }
}

object Export extends TypedReactSpec with TypedEventListeners {
  case class Props()
  case class State()

  def getInitialState(self: This) = State()

  implicit class Closure(self: This) {
    def focus(ref: String) = button.onClick(e => {
      val elem = self.refs(ref)
      Focusable.cast(elem).foreach(_.focus())
    })
  }

  @scalax
  def render(self: This) = {
    val margin = Map("margin" -> "10px")

    <div>
      <div style={margin}>
        {
          ContentEditable(ContentEditable.Props(), ref = "input-1")
        }
        <button onClick={self.focus("input-1")}>Focus</button>
      </div>
      <div style={margin}>
        {
          ContentEditable(ContentEditable.Props(), ref = "input-2")
        }
        <button onClick={self.focus("input-2")}>Focus</button>
      </div>
    </div>
  }
}
