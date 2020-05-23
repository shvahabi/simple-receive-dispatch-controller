package entity.form

import entity.orm

case class CarPlate(state: Int, area: String, serial: Int, random: Int) {
  def concat: String = serial.toString + area.toString + random.toString + state.toString
  def transactify: orm.CarPlate = orm.CarPlate(concat, state.toString, serial.toString, area, random.toString)
}
