package com.xored.scalajs.react.examples.children

import com.xored.scalajs.react._
import org.scalajs.dom._
import com.xored.scalajs.react.util.ClassName

object Children {

  object Button extends TypedReactSpec {
    case class State()
    case class Props(size: String, label: String, primary: Boolean)

    /**
     * Convenience method to create component
     */
    def apply(size: String, label: String, primary: Boolean): ComponentType =
      apply(Props(size, label, primary))

    /**
     * Convenience method to create component
     */
    def apply(size: String, label: String): ComponentType =
      apply(Props(size, label, primary = false))

    def getInitialState(self: This) = State()

    @scalax
    def render(self: This) = {
      val className = ClassName(
        s"button-${self.props.size}" -> true,
        s"button-primary" -> self.props.primary)

      <button className={className}>{self.props.label}</button>
    }
  }

  object ButtonToolbar extends TypedReactSpec {
    case class Props(buttons: ReactDOM*)
    case class State()

    def getInitialState(self: This) = State()

    /**
     * Convenience method to create component
     */
    def apply(buttons: ReactDOM*): ComponentType =
      apply(Props(buttons: _*))

    @scalax
    def render(self: This) = {
      <div className="button-toolbar">
        {
          self.props.buttons
        }
      </div>
    }
  }

  def main(parent: HTMLElement) = {
    @scalax val buttons = <div className="toolbars">
      {
        ButtonToolbar(
          <label>Small</label>,
          Button("small", "Small Button"),
          Button("small", "Small Button", primary = true)
        )
      }
      {
        ButtonToolbar(
          <label>Large</label>,
          Button("large", "Large Button"),
          Button("large", "Large Button", primary = true)
        )
      }
    </div>

    React.renderComponent(buttons, document.body)
  }
}
