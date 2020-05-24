package com.bsp.receivedispatch.controller.orm.db

import scalikejdbc._

case class CalendarDay(concatinated: Int, year: Int, month: Int, day: Int) {
  def exists: Boolean =
    concatinated == (DB readOnly { implicit session => sql"SELECT concat FROM `calendar days` WHERE concat = ${concatinated}".map(rs => rs.int("concat")).single.apply }).getOrElse(" ")
  def toSql: String =
    if (exists)
      " ;"
    else
      f"""
      |INSERT INTO
      |`calendar days` (`concat`, `y`, `m`, `d`)
      |VALUES (${concatinated}, ${year}, ${month}, ${day});
      |""".stripMargin
}
