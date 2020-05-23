package entity.orm
import argonaut.Argonaut._
import entity.form

case class Transaction(jsonString: String) {
  private val jsonObject: entity.form.ReceivingForm = entity.form.receivingForm(jsonString.parse.toOption.asJson)
  //private val goodsOwner: Person = Person(jsonObject.goodsOwner.firstName, jsonObject.goodsOwner.surName, jsonObject.goodsOwner.nationalIDNo)
  private val client: Person = jsonObject.goodsOwner.transactify
  //private val representative: Person = Person(jsonObject.representative.firstName, jsonObject.representative.surName, jsonObject.representative.nationalIDNo)
  private val representative: Person = jsonObject.representative.transactify
  //private val truckNumber: CarPlate = CarPlate(jsonObject.truck.concat, jsonObject.truck.)
  private val truckNumber: CarPlate = jsonObject.truck.transactify

  private val calendarDay: CalendarDay = jsonObject.date.transactify

  private val thisTransactionGeneratedFormNumber = jsonObject.number //from query
  //private val transactingForm: Form = Form(thisTransactionGeneratedFormNumber, jsonObject.comments, jsonObject.number, jsonObject.partNumber, jsonString, goodsOwner.nationalIDNo, jsonObject.date.concat, truckNumber.concatination)

  private val form: Form = jsonObject.transactify._2
  private val received: ReceivingForm = jsonObject.transactify._1

  def toSql: String = client.toSql + representative.toSql + truckNumber.toSql + calendarDay.toSql + form.toSql(thisTransactionGeneratedFormNumber) + received.toSql(thisTransactionGeneratedFormNumber)
}
