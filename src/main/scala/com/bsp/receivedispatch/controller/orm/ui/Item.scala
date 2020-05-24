package com.bsp.receivedispatch.controller.orm.ui

import com.bsp.receivedispatch.controller.orm.db

case class Item(description: String, quantity: Int, unitOfMeasurement: String, grossWeight: Double, packageWeight: Double, netWeight: Double) {
  def transactify: db.Item = db.Item(description, quantity, unitOfMeasurement, grossWeight, packageWeight, netWeight)
}
