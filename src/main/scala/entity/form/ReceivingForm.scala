package entity.form

import argonaut.Json
import entity.orm

case class ReceivingForm(goodsOwner: Person, number: Int, date: Date, partNumber: Int, truck: CarPlate, billOfLading: String, origin: String, itemsList:List[Item], comments: String, representative: Person, inJson: Json) {
  def transactify: (orm.ReceivingForm, orm.Form) = (orm.ReceivingForm(origin, billOfLading, representative.nationalIDNo), orm.Form(comments, number, partNumber, inJson.toString, goodsOwner.nationalIDNo, date.concat, truck.concat))
  //def transactify1: transaction.Form = transaction.Form(comments, number, partNumber, inJson.toString, goodsOwner.nationalIDNo, date.concat, truck.concat)

}