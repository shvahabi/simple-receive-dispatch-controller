package com.bsp.receivedispatch.controller.orm.ui

import argonaut.Json


class Form(json: Json) {
  def toJson = json
  //def transactify: orm.Form = orm.Form(comments, paperNumber, partNumber, inJson.toString, client.nationalIDNo, date.concat, truck.concat)
}
