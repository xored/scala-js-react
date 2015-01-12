package com.xored.scalajs.react

import scala.scalajs.js
import com.xored.scalajs.react.comp._
import utest._

object ReactRenderSuite extends TestSuite {
  // TODO use React.addons.TestUtils and jsdom

  def renderToString(dom: ReactDOM) = React.renderComponentToStaticMarkup(dom)

  val tests = TestSuite {
    "React#renderComponent" - {
      "render <HelloWorld />" - {
        val reactDom = HelloWorld(HelloWorld.Props())
        val dom = renderToString(reactDom)

        assert(dom == "<h1>Hello World!</h1>")
      }

      "render <HelloName />" - {
        val reactDom = Hello(Hello.Props("Jack"))
        val dom = renderToString(reactDom)

        assert(dom == "<h1>Hello Jack!</h1>")
      }

      "render <h1>Hello World</h1>" - {
        @scalax val reactDom = <h1>Hello World!</h1>
        val dom = renderToString(reactDom)

        assert(dom == "<h1>Hello World!</h1>")
      }

      "render <div><Hello World /></div>" - {
        @scalax val reactDom = <div>{HelloWorld(HelloWorld.Props())}</div>
        val dom = renderToString(reactDom)

        assert(dom == "<div><h1>Hello World!</h1></div>")
      }

      "render <Panel><HelloWorld /><button>Submit</button></Panel>" - {
        @scalax val reactDom = Panel(Panel.Props(
          HelloWorld(HelloWorld.Props()),
            <button>Submit</button>
          ))
        val dom = renderToString(reactDom)

        assert(dom == "<div class=\"panel\"><h1>Hello World!</h1><button>Submit</button></div>")
      }
    }
  }
}
