package com.bsp.receivedispatch.controller.orm.ui

import argonaut.Argonaut.casecodec6
import argonaut.CodecJson
import com.bsp.receivedispatch.controller.orm.db

case class Item(description: String, quantity: Int, unitOfMeasurement: String, grossWeight: Double, packageWeight: Double, netWeight: Double) {
  def transactify: db.Item = db.Item(description, quantity, unitOfMeasurement, grossWeight, packageWeight, netWeight)
}

object Item {
  implicit def codec: CodecJson[Item] = casecodec6(Item.apply, Item.unapply)("Description", "Quantity", "UnitOfMeasurement", "GrossWeight", "PackageWeight", "NetWeight")
}
