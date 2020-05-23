package entity

import argonaut._, Argonaut._

package object form {
  implicit def personEncodeToJson: EncodeJson[Person] =
    jencode3L((p: Person) => (p.firstName, p.surName, p.nationalIDNo))("FirstName", "SurName", "NationalIDNo")
  implicit def personDecodeFromJson: DecodeJson[Person] =
    jdecode3L(Person.apply)("FirstName", "SurName", "NationalIDNo")

  implicit def dateEncodeToJson: EncodeJson[Date] =
    jencode3L((d: Date) => (d.year, d.month, d.day))("Year", "Month", "Day")
  implicit def dateDecodeFromJson: DecodeJson[Date] =
    jdecode3L(Date.apply)("Year", "Month", "Day")

  implicit def carPlateEncodeToJson: EncodeJson[CarPlate] =
    jencode4L((cp: CarPlate) => (cp.state, cp.serial, cp.area, cp.random))("State", "Serial", "Area", "Random")
  implicit def carPlateDecodeFromJson: DecodeJson[CarPlate] =
    jdecode4L(CarPlate.apply)("State", "Serial", "Area", "Random")

  implicit def itemEncodeToJson: EncodeJson[Item] =
    jencode6L((i: Item) => (i.description, i.quantity, i.unitOfMeasurement, i.grossWeight, i.packageWeight, i.netWeight))("Description", "Quantity", "UnitOfMeasurement", "GrossWeight", "PackageWeight", "NetWeight")
  implicit def itemDecodeFromJson: DecodeJson[Item] =
    jdecode6L(Item.apply)("Description", "Quantity", "UnitOfMeasurement", "GrossWeight", "PackageWeight", "NetWeight")

  def person(json: Json): Person = (json.hcursor --\ "GoodsOwner").as[Json].value.get.as[Person].toOption.get
  def formNo(json: Json): Int = (json.hcursor --\ "FormNo").as[Json].value.get.as[Int].toOption.get
  def date(json: Json): Date = (json.hcursor --\ "ReceivedDate").as[Json].value.get.as[Date].toOption.get
  def part(json: Json): Int = (json.hcursor --\ "PartNo").as[Json].value.get.as[Int].toOption.get
  def carPlate(json: Json): CarPlate = (json.hcursor --\ "TruckNo").as[Json].value.get.as[CarPlate].toOption.get
  def location(json: Json): String = (json.hcursor --\ "Origin").as[Json].value.get.as[String].toOption.get
  def bOL(json: Json): String = (json.hcursor --\ "BillOfLadingNo").as[Json].value.get.as[String].toOption.get
  def comments(json: Json): String = (json.hcursor --\ "Comments").as[Json].value.get.as[String].toOption.get
  def representative(json: Json): Person = (json.hcursor --\ "Representative").as[Json].value.get.as[Person].toOption.get
  def itemsList(json: Json): List[Item] = (json.hcursor --\ "ItemsList").as[List[Json]].value.get.map(_.as[Item].value.get)

  def receivingForm(json: Json): ReceivingForm = ReceivingForm(person(json), formNo(json), date(json), part(json), carPlate(json), bOL(json), location(json), itemsList(json), comments(json), representative(json), json)
}
