package org.scalajs.react.util

object ClassName {
  def apply(classNameMap: (String, Boolean)*) = {
    for {
      (className, show) <- classNameMap if show
    } yield className
  }.mkString(" ")
}
