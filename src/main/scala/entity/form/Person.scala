package entity.form

import entity.orm

case class Person(firstName: String, surName: String, nationalIDNo: String) {
  def transactify: orm.Person = orm.Person(firstName, surName, nationalIDNo)
}
