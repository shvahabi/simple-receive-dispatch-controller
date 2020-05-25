package com.bsp.receivedispatch.controller.orm.ui

import argonaut.Argonaut.{jdecode3L, jencode3L}
import argonaut.{DecodeJson, EncodeJson}
import com.bsp.receivedispatch.controller.orm.db

case class Date(year:Int, month: Int, day: Int) {
  def concat: Int = (("0000" + year.toString takeRight 4) + ("00" + month.toString takeRight 2) + ("00" + day.toString takeRight 2)).toInt
  def transactify: db.CalendarDay = db.CalendarDay(concat, year, month, day)
  implicit def dateEncodeToJson: EncodeJson[Date] =
    jencode3L((d: Date) => (d.year, d.month, d.day))("Year", "Month", "Day")
  implicit def dateDecodeFromJson: DecodeJson[Date] =
    jdecode3L(Date.apply)("Year", "Month", "Day")

}
