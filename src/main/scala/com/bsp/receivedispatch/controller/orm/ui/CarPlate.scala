package com.bsp.receivedispatch.controller.orm.ui

import argonaut.Argonaut.casecodec4
import argonaut.CodecJson
import com.bsp.receivedispatch.controller.orm.db

case class CarPlate(state: Int, serial: Int, area: String, random: Int) {
  def concat: String = serial.toString + area + random.toString + state.toString
  def transactify: db.CarPlate = db.CarPlate(concat, state.toString, serial.toString, area, random.toString)
}

object CarPlate {
  implicit def codec: CodecJson[CarPlate] = casecodec4(CarPlate.apply, CarPlate.unapply)("State", "Serial", "Area", "Random")
}
