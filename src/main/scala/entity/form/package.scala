package entity

import argonaut.Argonaut._
import argonaut.{DecodeJson, EncodeJson, Json}

package object form {

  implicit def PersonEncodeToJson: EncodeJson[Person] =
    jencode3L((p: Person) => (p.firstName, p.surName, p.nationalIDNo))("FirstName", "SurName", "NationalIDNo")

  implicit def PersonDecodeFromJson: DecodeJson[Person] =
    jdecode3L(Person.apply)("FirstName", "SurName", "NationalIDNo")

  implicit def DateEncodeToJson: EncodeJson[Date] =
    jencode3L((d: Date) => (d.year, d.month, d.day))("Year", "Month", "Day")

  implicit def DateDecodeFromJson: DecodeJson[Date] =
    jdecode3L(Date.apply)("Year", "Month", "Day")

  implicit def CarPlateEncodeToJson: EncodeJson[CarPlate] =
    jencode4L((cp: CarPlate) => (cp.state, cp.area, cp.serial, cp.random))("State", "Serial", "Area", "Random")

  implicit def CarPlateDecodeFromJson: DecodeJson[CarPlate] =
    jdecode4L(CarPlate.apply)("State", "Serial", "Area", "Random")

  def person(json: Json): Person = (json.hcursor --\ "GoodsOwner").as[Json].value.get.as[Person].toOption.get
  def formNo(json: Json): Int = (json.hcursor --\ "FormNo").as[Json].value.get.as[Int].toOption.get
  def date(json: Json): Date = (json.hcursor --\ "ReceivedDate").as[Json].value.get.as[Date].toOption.get
  def part(json: Json): Int = (json.hcursor --\ "PartNo").as[Json].value.get.as[Int].toOption.get
  def carPlate(json: Json): CarPlate = (json.hcursor --\ "truckNo").as[Json].value.get.as[CarPlate].toOption.get
  def location(json: Json): String = (json.hcursor --\ "Origin").as[Json].value.get.as[String].toOption.get
  def bOL(json: Json): String = (json.hcursor --\ "BillOfLadingNo").as[Json].value.get.as[String].toOption.get
  def itemsList(json: Json): List[Item] = (json.hcursor --\ "ItemsList").as[Json].value.get.as(List[Item]).getOrElse(Nil)
  def comments(json: Json): String = (json.hcursor --\ "Comments").as[Json].value.get.as[String].toOption.get
  def representative(json: Json): Person = (json.hcursor --\ "Representative").as[Json].value.get.as[Person].toOption.get
  def receivingForm(json:Json): ReceivingForm = ReceivingForm(person(json), formNo(json), date(json), part(json), carPlate(json), bOL(json), location(json), itemsList(json), comments(json), representative(json))

  /*
  implicit def ItemEncodeToJson: EncodeJson[Item] =
    jencode6L((it: Item) => (it.description, it.quantity, it.unitOfMeasurement, it.grossWeight, it.packageWeight, it.netWeight))("Title", "Quantity",  "UnitOfMeasurement", "GrossWeight", "PackageWeight", "NetWeight")

  implicit def ItemPlateDecodeFromJson: DecodeJson[Item] =
    jdecode6L(Item.apply)("Title", "Quantity",  "UnitOfMeasurement", "GrossWeight", "PackageWeight", "NetWeight")
   */


}
