package com.bsp.receivedispatch.controller.orm.ui

import argonaut.Json
import com.bsp.receivedispatch.controller.orm.db

case class ReceivingForm(goodsOwner: Person, number: Int, date: Date, partNumber: Int, truck: CarPlate, billOfLading: String, origin: String, itemsList:List[Item], comments: String, representative: Person, inJson: Json) {
  def transactify: (db.ReceivingForm, db.Form) = (db.ReceivingForm(origin, billOfLading, representative.nationalIDNo), db.Form(comments, number, partNumber, inJson.toString, goodsOwner.nationalIDNo, date.concat, truck.concat))
  //def transactify1: transaction.Form = transaction.Form(comments, number, partNumber, inJson.toString, goodsOwner.nationalIDNo, date.concat, truck.concat)

}