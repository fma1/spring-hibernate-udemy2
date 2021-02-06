package org.hibernatewrapper.util

import java.lang.reflect.Field

object HBUtil {
  def reflectToString(o: AnyRef): String = {
    val clazz = o.getClass
    val fieldStrAry = clazz.getDeclaredFields.map { field: Field =>
      field.setAccessible(true)
      field.getName + ": " + field.get(o).toString
    }.mkString(",")
    s"${clazz.getSimpleName}(${fieldStrAry})"
  }
}
