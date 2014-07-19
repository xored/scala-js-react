package com.xored.scalajs.react.examples.todomvc

import com.xored.scalajs.react._
import com.xored.scalajs.react.util._
import org.scalajs.dom._

import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom.extensions.KeyCode

@JSExport("TodoApp")
object TodoApp extends TypedReactSpec with TypedEventListeners {

  case class Props()

  case class State(text: String, editingTodoUuid: Option[String], todos: Vector[Todo])

  def getInitialState(self: This) = State(
    text = "",
    editingTodoUuid = None,
    todos = Vector())

  implicit class Closure(self: This) {

    import self._

    val onChange = input.onChange(e => {
      setState(state.copy(text = e.target.value))
    })

    val onKeyPress = input.onKeyPress(e => {
      if (e.keyCode == KeyCode.enter) {
        e.preventDefault()

        val todo = Todo(uuid = UUID(), title = state.text, completed = false)

        setState(state.copy(
          text = "",
          todos = todo +: state.todos
        ))
      }
    })

    def onDestroy(todo: Todo) = {
      setState(state.copy(todos = state.todos.diff(Seq(todo))))
    }

    def onCancel() = {
      setState(state.copy(editingTodoUuid = None))
    }

    def onToggle(todo: Todo) = {
      val idx = state.todos.indexOf(todo)
      val newTodos = state.todos.updated(idx, todo.copy(completed = !todo.completed))

      setState(state.copy(todos = newTodos))
    }

    def onSave(todo: Todo, value: String) = {
      val idx = state.todos.indexOf(todo)
      val newTodos = state.todos.updated(idx, todo.copy(title = value))

      setState(state.copy(todos = newTodos, editingTodoUuid = None))
    }

    def onEdit(todo: Todo, callback: () => Unit) = {
      setState(state.copy(editingTodoUuid = Some(todo.uuid)), callback)
    }

    val clearCompleted = () => {
      val newTodos = state.todos.filterNot(_.completed)
      setState(state.copy(todos = newTodos))
    }

    val toggleAll = () => {
      val newTodos = state.todos.map {
        case todo if !todo.completed => todo.copy(completed = true)
        case x => x
      }

      setState(state.copy(todos = newTodos))
    }
  }

  @scalax
  def render(self: This) = {
    val activeTodoCount = self.state.todos.count(!_.completed)

    <section id="todoapp">
      <header id="header">
        <h1>todos</h1>
        <input id="new-todo"
               onChange={self.onChange}
               onKeyPress={self.onKeyPress}
               value={self.state.text}
               placeholder="What needs to be done?"
               autofocus={true}></input>
      </header>
      <section id="main">
        <input id="toggle-all"
               type="checkbox"
               checked={activeTodoCount == 0}
               onClick={self.toggleAll}
               readOnly={true}>
        </input>
        <ul id="todo-list">
        {
          for {
            todo <- self.state.todos
          } yield {
            TodoItem(TodoItem.Props(
              todo = todo,
              editing = self.state.editingTodoUuid.contains(todo.uuid),
              onDestroy = () => self.onDestroy(todo),
              onCancel = () => self.onCancel(),
              onToggle = () => self.onToggle(todo),
              onSave = value => self.onSave(todo, value),
              onEdit = callback => self.onEdit(todo, callback)
            ))
          }
        }
        </ul>
      </section>
      {
        if (self.state.todos.nonEmpty) {
          <footer id="footer">
            <span id="todo-count"><strong>{activeTodoCount}</strong> item left</span>
            <button id="clear-completed" onClick={self.clearCompleted}>
              Clear completed ({self.state.todos.size - activeTodoCount})
            </button>
          </footer>
        }
      }
    </section>
  }
}
