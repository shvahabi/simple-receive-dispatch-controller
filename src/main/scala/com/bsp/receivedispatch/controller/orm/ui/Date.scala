package com.bsp.receivedispatch.controller.orm.ui

import argonaut.Argonaut.casecodec3
import argonaut.CodecJson
import com.bsp.receivedispatch.controller.orm.db

case class Date(year:Int, month: Int, day: Int) {
  def concat: Int = (("0000" + year.toString takeRight 4) + ("00" + month.toString takeRight 2) + ("00" + day.toString takeRight 2)).toInt
  def transactify: db.CalendarDay = db.CalendarDay(concat, year, month, day)
}

object Date {
  implicit def codec: CodecJson[Date] = casecodec3(Date.apply, Date.unapply)("Year", "Month", "Day")
}