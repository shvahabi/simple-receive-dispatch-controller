package entity.orm

case class ReceivingForm(originOfGoods: String, billOfLadingNumber: String, nidOfRepresentative: String) {
  def toSql(formNumber: Long): String =
    f"""
       |INSERT INTO
       |`received` (`origin`, `bill of lading number`, `representative`,`form`)
       |VALUES ('${originOfGoods}', '${billOfLadingNumber}', ${nidOfRepresentative}, ${formNumber});
     """.stripMargin
}
