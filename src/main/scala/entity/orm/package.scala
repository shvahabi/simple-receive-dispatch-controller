package entity
import argonaut._
import Argonaut._

package object orm {

  //def jsonStringToSqlString(jsonString: String) =

  //def personToTransaction(person: entity.form.Person): Person = Person(person.firstName, person.surName, person.nationalIDNo)

  //def carPlateToTransaction(carPlate: entity.form.CarPlate): CarPlate = CarPlate(carPlate.concat, carPlate.state.toString, carPlate.serial.toString, carPlate.area, carPlate.random.toString)

  //def dateToTransaction(calendarDay: entity.form.Date): CalendarDay = CalendarDay(calendarDay.concat, calendarDay.year, calendarDay.month, calendarDay.day)

  /*

  def jsonToTransaction(jsonString: String): Form = {
    val jsonObject = jsonString.parse.toOption.asJson
    val receivedForm: entity.form.ReceivingForm = entity.form.receivingForm(jsonObject)
    val generatedFormNumber = 12 // to be replaced by a query

    Form(generatedFormNumber, receivedForm.comments, receivedForm.number, receivedForm.partNumber, jsonString, receivedForm.goodsOwner.nationalIDNo, receivedForm.date.concat, receivedForm.truck.concat)
  }

  def receivingToTransaction(receivedForm: entity.form.ReceivingForm): ReceivingForm = {
    val
  }

   */
}
