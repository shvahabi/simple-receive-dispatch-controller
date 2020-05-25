package com.bsp.receivedispatch.controller.orm

import argonaut.Argonaut._
import db._

case class DispatchingTransaction(jsonString: String) {
  def toSql: String = ???
}
