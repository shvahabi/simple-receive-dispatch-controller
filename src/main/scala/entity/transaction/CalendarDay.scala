package entity.transaction

case class CalendarDay(concatinated: Int, year: Int, month: Int, day: Int) {
  def toSql: String =
    f"""
       |INSERT INTO
       |`calendar days` (`concat`, `y`, `m`, `d`)
       |VALUES (${concatinated}, ${year}, ${month}, ${day});
     """.stripMargin
}
