package com.bsp.receivedispatch.controller

import com.bsp.receivedispatch.controller.orm.{DispatchingTransaction, ReceivingTransaction}
import org.scalatra._
import scalikejdbc._

class ReceiveDispatchServlet extends ScalatraServlet {

  before() {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:tcp://localhost//home/shahed/Documents/bsp/builds/version 0.14.0/assets/DB/DB2", "sa", "sa")
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = false,
      singleLineMode = false
    )
  }

  get("/receivedispatch/read/receipts/:id") {
    <p>contets for requested receipt form number is {params("id")}</p>
  }
  get("/receivedispatch/read/dispatches/:id") {
    <p>contents for requested dispatched form number is {params("id")}</p>
  }
  post("/receivedispatch/create/receipts") {
    DB localTx { implicit session =>
      SQL(ReceivingTransaction(request.body.intern).toSql).execute().apply()
    }
  }
  post("/receivedispatch/create/dispatches") {
    DB localTx { implicit session =>
      SQL(DispatchingTransaction(request.body.intern).toSql).execute().apply()
    }
  }
  delete("/receivedispatch/delete/receipts/:id") {
    <p>requested receipt form number to be deleted is {params("id")}</p>
  }
  delete("/receivedispatch/delete/dispatches/:id") {
    <p>requested dispatched form number to be deleted is {params("id")}</p>
  }
  put("/receivedispatch/update/receipts/:id") {
    <p>requested receipt form number to be updated is {params("id")}</p>
  }
  put("/receivedispatch/update/dispatches/:id") {
    <p>requested dispatched form number to be updated is {params("id")}</p>
  }

  get("/receivedispatch/people") {
    //todo: list of clients to be served here
  }


  get("/") {
    //todo: replaced with dashboard
    views.html.hello()
  }

}
