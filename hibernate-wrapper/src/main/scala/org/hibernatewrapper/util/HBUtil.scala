package org.hibernatewrapper.util

import java.lang.reflect.Field

object HBUtil {
  def reflectToString(o: AnyRef): String = {
    o.getClass.getDeclaredFields.map { field: Field =>
      field.setAccessible(true)
      field.getName + ": " + field.getType + " = " + field.get(o).toString
    }.mkString(",")
  }
}
