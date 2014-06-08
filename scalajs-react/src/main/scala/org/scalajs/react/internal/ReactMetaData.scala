package org.scalajs.react.internal

import scala.scalajs.js

trait ReactMetaData

object ReactMetaData {

  def empty() = js.Dictionary.empty[js.Any]

  def apply(key: String, value: Option[(_) => _], md: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    value match {
      case Some(x) => apply(key, x, md)
      case None => md
    }
  }

  def apply(key: String, value: Map[String, String], md: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    md.update(key, js.Dictionary.apply(value.toSeq: _*))
    md
  }

  def apply(key: String, value: () => _, md: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    md.update(key, js.Any.fromFunction0(value))
    md
  }

  def apply(key: String, value: ( _ ) => _, md: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    md.update(key, js.Any.fromFunction1(value))
    md
  }

  def apply(key: String, value: (_, _) => _, md: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    md.update(key, js.Any.fromFunction2(value))
    md
  }

  def apply(key: String, value: (_, _, _) => _, md: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    md.update(key, js.Any.fromFunction3(value))
    md
  }

  def apply(key: String, value: js.String, md: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    md.update(key, value)
    md
  }

  def apply(key: String, value: js.Boolean, md: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    md.update(key, value)
    md
  }

  def apply(key: String, value: js.Number, md: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    md.update(key, value)
    md
  }

}
