package com.xored.scalajs.react

import scala.scalajs.test.JasmineTest
import scala.scalajs.js
import com.xored.scalajs.react.comp._

object ReactRenderSuite extends JasmineTest {
  // TODO use React.addons.TestUtils and jsdom

  def renderToString(dom: ReactDOM) = React.renderComponentToStaticMarkup(dom)

  describe("React#renderComponent") {
    it("render <HelloWorld />") {
      val reactDom = HelloWorld(HelloWorld.Props())
      val dom = renderToString(reactDom)

      expect(dom).toEqual("<h1>Hello World!</h1>")
    }

    it("render <HelloName />") {
      val reactDom = Hello(Hello.Props("Jack"))
      val dom = renderToString(reactDom)

      expect(dom).toEqual("<h1>Hello Jack!</h1>")
    }

    it("render <h1>Hello World</h1>") {
      @scalax val reactDom = <h1>Hello World!</h1>
      val dom = renderToString(reactDom)

      expect(dom).toEqual("<h1>Hello World!</h1>")
    }

    it("render <div><Hello World /></div>") {
      @scalax val reactDom = <div>{HelloWorld(HelloWorld.Props())}</div>
      val dom = renderToString(reactDom)

      expect(dom).toEqual("<div><h1>Hello World!</h1></div>")
    }

    it("render <Panel><HelloWorld /><button>Submit</button></Panel>") {
      @scalax val reactDom = Panel(Panel.Props(
            HelloWorld(HelloWorld.Props()),
            <button>Submit</button>
          ))
      val dom = renderToString(reactDom)

      expect(dom).toEqual("<div class=\"panel\"><h1>Hello World!</h1><button>Submit</button></div>")
    }
  }
}
