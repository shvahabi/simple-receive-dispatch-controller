package com.bsp.receivedispatch.controller.orm.ui

import argonaut.Argonaut.casecodec3
import argonaut.CodecJson
import com.bsp.receivedispatch.controller.orm.db

case class Person(firstName: String, surName: String, nationalIDNo: String) {
  def transactify: db.Person = db.Person(firstName, surName, nationalIDNo)
}

object Person {
  implicit def codec: CodecJson[Person] = casecodec3(Person.apply, Person.unapply)("FirstName", "SurName", "NationalIDNo")
}
