package org.scalajs.react

import scala.scalajs.js

package object util {

  implicit class RichDictionary[T](val dict: js.Dictionary[T]) {
    def += (kv: (String, T)): js.Dictionary[T] = {
      dict.update(kv._1, kv._2)
      dict
    }
  }

}
