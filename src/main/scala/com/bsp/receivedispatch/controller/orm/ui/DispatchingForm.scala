package com.bsp.receivedispatch.controller.orm.ui

import argonaut.Json
import com.bsp.receivedispatch.controller.orm.db

case class DispatchingForm(goodsOwner: Person, number: Int, date: Date, partNumber: Int, truck: CarPlate, draftDate: Date, draftNumber: String, beneficiary: Person, itemsList:List[Item], comments: String, assignee: Person, inJson: Json) {
  def transactify: (db.DispatchingForm, db.Form) = (db.DispatchingForm(draftDate.concat, draftNumber, beneficiary.nationalIDNo, assignee.nationalIDNo), db.Form(comments, number, partNumber, inJson.toString, goodsOwner.nationalIDNo, date.concat, truck.concat))
}
