package com.bsp.receivedispatch.controller.orm.db

import scalikejdbc._

case class CarPlate(concatination: String, stateCode: String, serialNumber: String, areaCode: String, randomNumber: String) {
  def exists: Boolean =
    concatination == (DB readOnly { implicit session => sql"SELECT concat FROM carplates WHERE concat = ${concatination}".map(rs => rs.string("concat")).single.apply }).getOrElse(" ")
  def toSql: String =
    if (exists)
      " ;"
    else
      f"""
      |INSERT INTO
      |`carplates` (`concat`, `statecode`, `serial`, `areacode`, `random`)
      | VALUES('${concatination}', '${stateCode}', '${serialNumber}', '${areaCode}', '${randomNumber}');
      | """.stripMargin
}
