package com.xored.scalajs.react.examples.todomvc

import com.xored.scalajs.react._
import com.xored.scalajs.react.util._
import org.scalajs.dom._

import org.scalajs.dom.extensions.KeyCode

object TodoItem extends TypedReactSpec with TypedEventListeners {

  case class Props(
    todo: Todo,
    editing: Boolean,
    onDestroy: () => Unit,
    onCancel: () => Unit,
    onToggle: () => Unit,
    onEdit: ( () => Unit ) => Unit,
    onSave: String => Unit)

  case class State(editText: String)

  def getInitialState(self: This) = State(self.props.todo.title)

  val editField = "editField"

  implicit class Closure(self: This) {

    import self._

    val handleChange = input.onChange(e => {
      self.setState(state.copy(editText = e.target.value))
    })

    val handleEdit = element.onEvent(e => {
      props.onEdit(() => {
        val node = self.refs(editField).getDOMNode[HTMLInputElement]()
        node.focus()
        node.setSelectionRange(node.value.length, node.value.length)
      })

      e.preventDefault()
    })

    val handleSubmit = element.onEvent(e => {
      val value = self.state.editText.trim()

      if (value.nonEmpty) {
        props.onSave(value)
        setState(self.state.copy(editText = value))
      } else {
        self.props.onDestroy()
      }

      e.preventDefault()
    })

    val handleKeyDown = input.onKeyPress(e => {
      if (e.keyCode == KeyCode.escape) {
        setState(state.copy(editText = self.props.todo.title))
        props.onCancel()
      } else if (e.keyCode == KeyCode.enter) {
        handleSubmit(e)
      }
    })
  }

  override def key(props: This#Props) = props.todo.uuid

  @scalax
  def render(self: This) = {
    val className = ClassName(
      "complete" -> self.props.todo.completed,
      "editing" -> self.props.editing)

    <li className={className}>
      <div className="view">
        <input className="toggle"
               type="checkbox"
               checked={self.props.todo.completed}
               onChange={self.props.onToggle}>
        </input>
        <label onDoubleClick={self.handleEdit}>{self.props.todo.title}</label>
        <button className="destroy" onClick={self.props.onDestroy}></button>
      </div>
      <input className="edit"
             value={self.state.editText}
             ref={editField}
             onBlur={self.handleSubmit}
             onChange={self.handleChange}
             onKeyDown={self.handleKeyDown}>
      </input>
    </li>
  }
}