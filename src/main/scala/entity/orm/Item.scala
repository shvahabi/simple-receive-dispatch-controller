package entity.orm

case class Item(description: String, quantity: Int, unitOfMeasurement: String, grossWeight: Double, packageWeight: Double, netWeight: Double) {
  def toSql(formNumber: Long): String =
    s"""
      |INSERT INTO
      |`goods` (`description`, `qty`, `unit`, `gweight` , `pweight`, `nweight`, `form`)
      |VALUES('${description}', ${quantity}, '${unitOfMeasurement}', ${grossWeight}, ${packageWeight}, ${netWeight}, ${formNumber});
      |""".stripMargin
}
