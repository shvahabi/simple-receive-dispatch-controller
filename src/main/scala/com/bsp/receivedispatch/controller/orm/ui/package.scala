package com.bsp.receivedispatch.controller.orm

import argonaut._, Argonaut._

package object ui {
  def client(json: Json): Person = (json.hcursor --\ "GoodsOwner").as[Json].value.get.as[Person].toOption.get
  def formNo(json: Json): Int = (json.hcursor --\ "FormNo").as[Json].value.get.as[Int].toOption.get
  def receivedDate(json: Json): Date = (json.hcursor --\ "ReceivedDate").as[Json].value.get.as[Date].toOption.get
  def part(json: Json): Int = (json.hcursor --\ "PartNo").as[Json].value.get.as[Int].toOption.get
  def carPlate(json: Json): CarPlate = (json.hcursor --\ "TruckNo").as[Json].value.get.as[CarPlate].toOption.get
  def location(json: Json): String = (json.hcursor --\ "Origin").as[Json].value.get.as[String].toOption.get
  def bOL(json: Json): String = (json.hcursor --\ "BillOfLadingNo").as[Json].value.get.as[String].toOption.get
  def comments(json: Json): String = (json.hcursor --\ "Comments").as[Json].value.get.as[String].toOption.get
  def representative(json: Json): Person = (json.hcursor --\ "Representative").as[Json].value.get.as[Person].toOption.get

  def dispatchedDate(json: Json): Date = (json.hcursor --\ "DispatchedDate").as[Json].value.get.as[Date].toOption.get
  def assignee(json: Json): Person = (json.hcursor --\ "Assignee").as[Json].value.get.as[Person].toOption.get
  def beneficiary(json: Json): Person = (json.hcursor --\ "DraftOwner").as[Json].value.get.as[Person].toOption.get
  def draftDate(json: Json): Date = (json.hcursor --\ "DraftDate").as[Json].value.get.as[Date].toOption.get
  def draftNumber(json: Json): String = (json.hcursor --\ "DraftNo").as[Json].value.get.as[String].toOption.get

  def itemsList(json: Json): List[Item] = (json.hcursor --\ "ItemsList").as[List[Json]].value.get.map(_.as[Item].value.get)

  def receivingForm(json: Json): ReceivingForm = ReceivingForm(client(json), formNo(json), receivedDate(json), part(json), carPlate(json), bOL(json), location(json), itemsList(json), comments(json), representative(json), json)
  def dispatchingForm(json: Json): DispatchingForm = DispatchingForm(client(json), formNo(json), dispatchedDate(json), part(json), carPlate(json), draftDate(json), draftNumber(json), beneficiary(json), itemsList(json), comments(json), assignee(json), json)

}
