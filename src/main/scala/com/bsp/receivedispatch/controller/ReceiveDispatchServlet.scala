package com.bsp.receivedispatch.controller

import com.bsp.receivedispatch.controller.orm.Transaction
import org.scalatra._
import scalikejdbc._

class ReceiveDispatchServlet extends ScalatraServlet {

  before() {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:tcp://localhost//home/shahed/Documents/bsp/builds/version 0.11.0/assets/DB/DB1", "sa", "sa")
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = false,
      singleLineMode = false
    )
  }


  get("/") {
    views.html.hello()
  }
  

  post("/forms/receiving") {

    DB localTx { implicit session =>
      SQL(Transaction(request.body.toString).toSql).execute().apply()
    }

  }

}
