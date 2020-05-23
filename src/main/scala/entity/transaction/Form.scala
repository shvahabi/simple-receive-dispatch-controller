package entity.transaction

case class Form(formNumber: Long, comments: String, paperNumber: Int, partNumber: Int, submittedJson: String, nidOfClient: String, formDate: Int, truckNumber: String) {
  def toSql: String =
    f"""
       |INSERT INTO
       |`forms` (`no`, `comments`, `paper number`, `part number`, `submitted`, `client`, `date`, `truck number`)
       |VALUES (${formNumber}, '${comments}', ${paperNumber}, ${partNumber}, '${submittedJson}', '${nidOfClient}', ${formDate}, '${truckNumber}');
     """.stripMargin
}
