package com.bsp.receivedispatch.controller.orm.ui

import argonaut.Argonaut.{jdecode6L, jencode6L}
import argonaut.{DecodeJson, EncodeJson}
import com.bsp.receivedispatch.controller.orm.db

case class Item(description: String, quantity: Int, unitOfMeasurement: String, grossWeight: Double, packageWeight: Double, netWeight: Double) {
  def transactify: db.Item = db.Item(description, quantity, unitOfMeasurement, grossWeight, packageWeight, netWeight)
  implicit def itemEncodeToJson: EncodeJson[Item] =
    jencode6L((i: Item) => (i.description, i.quantity, i.unitOfMeasurement, i.grossWeight, i.packageWeight, i.netWeight))("Description", "Quantity", "UnitOfMeasurement", "GrossWeight", "PackageWeight", "NetWeight")
  implicit def itemDecodeFromJson: DecodeJson[Item] =
    jdecode6L(Item.apply)("Description", "Quantity", "UnitOfMeasurement", "GrossWeight", "PackageWeight", "NetWeight")
}
