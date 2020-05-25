package com.bsp.receivedispatch.controller.orm.ui

import argonaut.Argonaut.{jdecode4L, jencode4L}
import argonaut.{DecodeJson, EncodeJson}
import com.bsp.receivedispatch.controller.orm.db

case class CarPlate(state: Int, serial: Int, area: String, random: Int) {
  def concat: String = serial.toString + area.toString + random.toString + state.toString
  def transactify: db.CarPlate = db.CarPlate(concat, state.toString, serial.toString, area, random.toString)
  implicit def carPlateEncodeToJson: EncodeJson[CarPlate] =
    jencode4L((cp: CarPlate) => (cp.state, cp.serial, cp.area, cp.random))("State", "Serial", "Area", "Random")
  implicit def carPlateDecodeFromJson: DecodeJson[CarPlate] =
    jdecode4L(CarPlate.apply)("State", "Serial", "Area", "Random")
}
