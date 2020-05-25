package com.bsp.receivedispatch.controller.orm.db

import scalikejdbc._

case class Person(firstName: String, surName: String, nationalIDNo: String) {
  def exists: Boolean =
    nationalIDNo == (DB readOnly { implicit session => sql"SELECT nationalidno FROM people WHERE nationalidno = '${nationalIDNo}'".map(rs => rs.string("nationalidno")).single.apply }).getOrElse(" ")
  def toSql: String =
    if (exists)
      " ;"
    else
      f"""
      |INSERT INTO
      |`people` (`nationalidno`, `firstname`, `surname`)
      |VALUES ('${nationalIDNo}', '${firstName}', '${surName}');
      |""".stripMargin
}
