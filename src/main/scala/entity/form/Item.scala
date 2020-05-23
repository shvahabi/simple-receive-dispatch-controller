package entity.form

import entity.orm

case class Item(description: String, quantity: Int, unitOfMeasurement: String, grossWeight: Double, packageWeight: Double, netWeight: Double) {
  def transactify: orm.Item = orm.Item(description, quantity, unitOfMeasurement, grossWeight, packageWeight, netWeight)
}
