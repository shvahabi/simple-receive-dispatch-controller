package com.bsp.receivedispatch.controller.orm.ui

import argonaut.Argonaut.{jdecode3L, jencode3L, casecodec3}
import argonaut.{CodecJson, DecodeJson, EncodeJson}
import com.bsp.receivedispatch.controller.orm.db

case class Person(firstName: String, surName: String, nationalIDNo: String) {
  def transactify: db.Person = db.Person(firstName, surName, nationalIDNo)


}

object Person {
  implicit def PersonCodecJson: CodecJson[Person] = casecodec3(Person.apply, Person.unapply)("FirstName", "SurName", "NationalIDNo")
}
