package com.bsp.receivedispatch.controller.orm.ui

import com.bsp.receivedispatch.controller.orm.db

case class Date(year:Int, month: Int, day: Int) {
  def concat: Int = (year.toString + month.toString + day.toString ).toInt
  def transactify: db.CalendarDay = db.CalendarDay(concat, year, month, day)
}
