package entity.transaction

class Transaction() {
  def toSql: String =
    Person().toSql
}
