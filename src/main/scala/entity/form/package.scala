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
    jdecode4L(CarPlate.apply)("State", "Area", "Serial","Random")


  def receivingForm(json: Json): ReceivingForm = {

    val person: Person = (json.hcursor --\ "GoodsOwner").as[Json].value.get.as[Person].toOption.get
    //def objectify[T](json: Json, key: String): T = (json.hcursor --\ key).as[Json].value.get.as[T].toOption.get
    //def person(json: Json): Person = objectify[Person](json, "GoodsOwner")
    val formNo: Int = (json.hcursor --\ "FormNo").as[Json].value.get.as[Int].toOption.get
    val date: Date = (json.hcursor --\ "ReceivedDate").as[Json].value.get.as[Date].toOption.get
    val part: Int = (json.hcursor --\ "PartNo").as[Json].value.get.as[Int].toOption.get
    val carPlate: CarPlate = (json.hcursor --\ "TruckNo").as[Json].value.get.as[CarPlate].toOption.get
    val location: String = (json.hcursor --\ "Origin").as[Json].value.get.as[String].toOption.get
    val bOL: String = (json.hcursor --\ "BillOfLadingNo").as[Json].value.get.as[String].toOption.get
    val comments: String = (json.hcursor --\ "Comments").as[Json].value.get.as[String].toOption.get
    val representative: Person = (json.hcursor --\ "Representative").as[Json].value.get.as[Person].toOption.get

    ReceivingForm(person, formNo, date, part, carPlate, bOL, location, comments, representative, json)
  }

  /*
  implicit def ItemEncodeToJson: EncodeJson[Item] =
    jencode6L((it: Item) => (it.description, it.quantity, it.unitOfMeasurement, it.grossWeight, it.packageWeight, it.netWeight))("Title", "Quantity", "UnitOfMeasurement", "GrossWeight", "PackageWeight", "NetWeight")

  implicit def ItemPlateDecodeFromJson: DecodeJson[Item] =
    jdecode6L(Item.apply)("Title", "Quantity", "UnitOfMeasurement", "GrossWeight", "PackageWeight", "NetWeight")
   */


}
