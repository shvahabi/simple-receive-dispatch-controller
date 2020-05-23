package entity.orm

case class Person(firstName: String, surName: String, nationalIDNo: String) {
  def toSql: String =
    f"""
       |INSERT INTO
       |`people` (`nationalidno`, `firstname`, `surname`)
       |VALUES ('${nationalIDNo}', '${firstName}', '${surName}');
     """.stripMargin
}
