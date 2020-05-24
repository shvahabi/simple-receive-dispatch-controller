package com.bsp.receivedispatch.controller.orm

import argonaut._, Argonaut._

package object ui {
  implicit def personEncodeToJson: EncodeJson[Person] =
    jencode3L((p: Person) => (p.firstName, p.surName, p.nationalIDNo))("firstName", "surName", "nationalidno")
  implicit def personDecodeFromJson: DecodeJson[Person] =
    jdecode3L(Person.apply)("firstname", "surname", "nationalidno")

  implicit def dateEncodeToJson: EncodeJson[Date] =
    jencode3L((d: Date) => (d.year, d.month, d.day))("year", "month", "day")
  implicit def dateDecodeFromJson: DecodeJson[Date] =
    jdecode3L(Date.apply)("year", "month", "day")

  implicit def carPlateEncodeToJson: EncodeJson[CarPlate] =
    jencode4L((cp: CarPlate) => (cp.state, cp.serial, cp.area, cp.random))("state", "serial", "area", "random")
  implicit def carPlateDecodeFromJson: DecodeJson[CarPlate] =
    jdecode4L(CarPlate.apply)("state", "serial", "area", "random")

  implicit def itemEncodeToJson: EncodeJson[Item] =
    jencode6L((i: Item) => (i.description, i.quantity, i.unitOfMeasurement, i.grossWeight, i.packageWeight, i.netWeight))("description", "quantity", "unitofmeasurement", "grossweight", "packageweight", "netweight")
  implicit def itemDecodeFromJson: DecodeJson[Item] =
    jdecode6L(Item.apply)("description", "quantity", "unitofmeasurement", "grossweight", "packageweight", "netweight")

  def person(json: Json): Person = (json.hcursor --\ "goodsowner").as[Json].value.get.as[Person].toOption.get
  def formNo(json: Json): Int = (json.hcursor --\ "formno").as[Json].value.get.as[Int].toOption.get
  def date(json: Json): Date = (json.hcursor --\ "receiveddate").as[Json].value.get.as[Date].toOption.get
  def part(json: Json): Int = (json.hcursor --\ "partno").as[Json].value.get.as[Int].toOption.get
  def carPlate(json: Json): CarPlate = (json.hcursor --\ "truckno").as[Json].value.get.as[CarPlate].toOption.get
  def location(json: Json): String = (json.hcursor --\ "origin").as[Json].value.get.as[String].toOption.get
  def bOL(json: Json): String = (json.hcursor --\ "billofladingno").as[Json].value.get.as[String].toOption.get
  def comments(json: Json): String = (json.hcursor --\ "comments").as[Json].value.get.as[String].toOption.get
  def representative(json: Json): Person = (json.hcursor --\ "representative").as[Json].value.get.as[Person].toOption.get
  def itemsList(json: Json): List[Item] = (json.hcursor --\ "itemslist").as[List[Json]].value.get.map(_.as[Item].value.get)

  def receivingForm(json: Json): ReceivingForm = ReceivingForm(person(json), formNo(json), date(json), part(json), carPlate(json), bOL(json), location(json), itemsList(json), comments(json), representative(json), json)
}
