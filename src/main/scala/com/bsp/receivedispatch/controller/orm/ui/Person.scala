package com.bsp.receivedispatch.controller.orm.ui

import com.bsp.receivedispatch.controller.orm.db

case class Person(firstName: String, surName: String, nationalIDNo: String) {
  def transactify: db.Person = db.Person(firstName, surName, nationalIDNo)
}
