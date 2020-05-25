package com.bsp.receivedispatch.controller

import com.bsp.receivedispatch.controller.orm.ui.Person
import com.bsp.receivedispatch.controller.orm.{DispatchingTransaction, ReceivingTransaction}
import org.scalatra._
import scalikejdbc._

class ReceiveDispatchServlet extends ScalatraServlet {

  before() {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:tcp://localhost//home/shahed/Documents/bsp/builds/controller/0.17.0/assets/DB/DB3", "sa", "sa")
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = false,
      singleLineMode = false
    )
  }

  get("/receivedispatch/receipts/:id") {
    println({params("id")})
    <p>contets for requested receipt form number is {params("id")}</p>
  }
  get("/receivedispatch/dispatches/:id") {
    <p>contents for requested dispatched form number is {params("id")}</p>
  }
  post("/receivedispatch/receipts") {
    println(request.body.intern)
    //<p>contents for requested dispatched form number is ${request.body.intern}</p>
    DB localTx { implicit session =>
      SQL(ReceivingTransaction(request.body.intern).toSql).execute().apply()
    }
  }
  post("/receivedispatch/dispatches") {
println(request.body.intern)
    <p>contents for requested dispatched form number is ${request.body.intern}</p>
    DB localTx { implicit session =>
      SQL(DispatchingTransaction(request.body.intern).toSql).execute().apply()
    }
  }
  delete("/receivedispatch/receipts/:id") {
    <p>requested receipt form number to be deleted is {params("id")}</p>
  }
  delete("/receivedispatch/dispatches/:id") {
    <p>requested dispatched form number to be deleted is {params("id")}</p>
  }
  put("/receivedispatch/receipts/:id") {
    <p>requested receipt form number to be updated is {params("id")}</p>
  }
  put("/receivedispatch/dispatches/:id") {
    <p>requested dispatched form number to be updated is {params("id")}</p>
  }

  get("/receivedispatch/people") {
    val people = DB readOnly { implicit session => sql"SELECT * FROM people".map(rs => Person(rs.string("firstname"), rs.string("surname"), rs.string("nationalidno"))).list.apply() }
    people.asJson
  }


  get("/") {

    views.html.hello()
  }

}
