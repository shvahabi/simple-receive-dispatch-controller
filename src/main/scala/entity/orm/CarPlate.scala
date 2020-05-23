package entity.orm

case class CarPlate(concatination: String, stateCode: String, serialNumber: String, areaCode: String, randomNumber: String) {
  def toSql: String =
    f"""
       |INSERT INTO
       |`carplates` (`concat`, `statecode`, `serial`, `areacode`, `random`)
       | VALUES('${concatination}', '${stateCode}', '${serialNumber}', '${areaCode}', '${randomNumber}');
     """.stripMargin
}
