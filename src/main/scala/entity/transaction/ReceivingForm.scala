package entity.transaction

case class ReceivingForm(originOfGoods: String, billOfLadingNumber: String, nidOfRepresentative, formNumber: Long) {
  def toSql: String =
    f"""
       |INSERT INTO
       |`received` (`origin`, `bill of lading number`, `representative`,`form`)
       |VALUES ('${originOfGoods}', '${billOfLadingNumber}', ${nidOfRepresentative}, ${formNumber});
     """.stripMargin
}
