package entity.form

import entity.orm

case class Date(year:Int, month: Int, day: Int) {
  def concat: Int = (year.toString + month.toString + day.toString ).toInt
  def transactify: orm.CalendarDay = orm.CalendarDay(concat, year, month, day)
}
