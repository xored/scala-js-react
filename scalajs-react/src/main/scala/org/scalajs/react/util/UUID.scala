package org.scalajs.react.util

import scala.scalajs.js

object UUID {

  def apply(): js.String = {
    val re = js.RegExp("[xy]", "g")
    val pattern: js.String = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx"

    def replaceFn(c: String) = {
      val r = ( js.Math.random() * 16 ).toLong | 0
      val v = if (c == "x") r else ( r & 0x3 ) | 0x8

      js.Any.fromLong(v).toString(16)
    }

    pattern.replace(re, js.Any.fromFunction1(replaceFn))
  }
}
