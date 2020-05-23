package entity.transaction

case class Person(nationalIdNumber: String, firstName: String, surName: String) {
  def toSql: String =
    f"""
       |INSERT INTO
       |`people` (`nationalidno`, `firstname`, `surname`)
       |VALUES ('${nationalIdNumber}', '${firstName}', '${surName}');
     """.stripMargin
}
