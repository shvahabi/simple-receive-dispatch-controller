package com.bsp.receivedispatch.controller.orm
import argonaut.Argonaut._

import db._

case class Transaction(jsonString: String) {
  private val jsonObject: ui.ReceivingForm = ui.receivingForm(jsonString.parse.toOption.asJson)
  //private val goodsOwner: Person = Person(jsonObject.goodsOwner.firstName, jsonObject.goodsOwner.surName, jsonObject.goodsOwner.nationalIDNo)
  private val client: Person = jsonObject.goodsOwner.transactify
  //private val representative: Person = Person(jsonObject.representative.firstName, jsonObject.representative.surName, jsonObject.representative.nationalIDNo)
  private val representative: Person = jsonObject.representative.transactify
  //private val truckNumber: CarPlate = CarPlate(jsonObject.truck.concat, jsonObject.truck.)
  private val truckNumber: CarPlate = jsonObject.truck.transactify

  private val calendarDay: CalendarDay = jsonObject.date.transactify

  private val thisTransactionGeneratedFormNumber = jsonObject.number //from query
  private val itemsList: List[Item] = jsonObject.itemsList.map(_.transactify)

  private val form: Form = jsonObject.transactify._2
  private val received: ReceivingForm = jsonObject.transactify._1

  def toSql: String = client.toSql + representative.toSql + truckNumber.toSql + calendarDay.toSql + form.toSql(thisTransactionGeneratedFormNumber) + itemsList.map(_.toSql(thisTransactionGeneratedFormNumber)).reduceLeft(_+_) + received.toSql(thisTransactionGeneratedFormNumber)
}

