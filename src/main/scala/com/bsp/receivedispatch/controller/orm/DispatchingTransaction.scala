package com.bsp.receivedispatch.controller.orm

import argonaut.Argonaut._
import db._

case class DispatchingTransaction(jsonString: String) {
  private val jsonObject: ui.DispatchingForm = ui.dispatchingForm(jsonString.parse.toOption.asJson)
  private val client: Person = jsonObject.goodsOwner.transactify
  private val truckNumber: CarPlate = jsonObject.truck.transactify
  private val date: CalendarDay = jsonObject.date.transactify
  private val thisTransactionGeneratedFormNumber = jsonObject.number //todo: from query
  private val itemsList: List[Item] = jsonObject.itemsList.map(_.transactify)

  private val form: Form = jsonObject.transactify._2
  private val dispatched: DispatchingForm = jsonObject.transactify._1

  private val assignee: Person = jsonObject.assignee.transactify
  private val beneficiary: Person = jsonObject.beneficiary.transactify
  private val draftDate: CalendarDay = jsonObject.draftDate.transactify

  def toSql: String = client.toSql + (if(beneficiary.toSql == client.toSql) " ;" else beneficiary.toSql) + (if((assignee.toSql == client.toSql) || (assignee.toSql == beneficiary.toSql)) " ;" else assignee.toSql) + truckNumber.toSql + date.toSql + (if(draftDate.toSql == date.toSql) " ;" else draftDate.toSql) + form.toSql(thisTransactionGeneratedFormNumber) + itemsList.map(_.toSql(thisTransactionGeneratedFormNumber)).reduceLeft(_+_) + dispatched.toSql(thisTransactionGeneratedFormNumber)
  

}
