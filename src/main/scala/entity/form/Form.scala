package entity.form

import argonaut.Json
import entity.orm

class Form(json: Json) {
  def toJson = json
  //def transactify: orm.Form = orm.Form(comments, paperNumber, partNumber, inJson.toString, client.nationalIDNo, date.concat, truck.concat)
}
