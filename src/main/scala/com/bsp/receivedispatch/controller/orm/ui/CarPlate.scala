package com.bsp.receivedispatch.controller.orm.ui

import com.bsp.receivedispatch.controller.orm.db

case class CarPlate(state: Int, serial: Int, area: String, random: Int) {
  def concat: String = serial.toString + area.toString + random.toString + state.toString
  def transactify: db.CarPlate = db.CarPlate(concat, state.toString, serial.toString, area, random.toString)
}
