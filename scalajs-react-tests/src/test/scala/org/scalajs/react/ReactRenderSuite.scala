package org.scalajs.react

import scala.scalajs.test.JasmineTest
import scala.scalajs.js
import org.scalajs.react.interop.ReactComponent

object ReactRenderSuite extends JasmineTest {
  js.eval("React = module.exports")

  // TODO use React.addons.TestUtils and jsdom

  def renderToString(component: ReactComponent[_, _]) =
    React.renderComponentToString(component)
      .replaceAll(" data-reactid=\"[^\"]*\"", "")
      .replaceAll(" data-react-checksum=\"[^\"]*\"", "")

  describe("Hello") {
    object Hello extends TypedReactSpec {
      case class Props()
      case class State()

      def getInitialState(self: This) = State()

      @scalax
      def render(self: This) = {
        <h1>Hello World!</h1>
      }
    }

    it("render") {
      val component = Hello(Hello.Props())
      val dom = renderToString(component)

      expect(dom).toEqual("<h1>Hello World!</h1>")
    }
  }

  describe("Hello with props") {
    object Hello extends TypedReactSpec {
      case class Props(name: String)
      case class State()

      def getInitialState(self: This) = State()

      @scalax
      def render(self: This) = {
        <h1>Hello {self.props.name}!</h1>
      }
    }

    it("render") {
      val component = Hello(Hello.Props("Jack"))
      val dom = renderToString(component)

      expect(dom).toEqual("<h1><span>Hello </span><span>Jack</span><span>!</span></h1>")
    }
  }

}
