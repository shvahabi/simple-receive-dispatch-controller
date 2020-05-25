package com.bsp.receivedispatch.controller

import com.bsp.receivedispatch.controller.orm.ui.Person
import com.bsp.receivedispatch.controller.orm.{DispatchingTransaction, ReceivingTransaction}
import org.scalatra._
import org.scalatra.CorsSupport
import scalikejdbc._
import argonaut.Argonaut._
import argonaut.Json
import org.scalatra.ActionResult._


class ReceiveDispatchServlet extends ScalatraServlet with CorsSupport {

  before() {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:tcp://localhost//home/shahed/Documents/bsp/builds/controller/0.30.1/assets/DB/DB7", "sa", "sa")
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = false,
      singleLineMode = false
    )
  }
options("/*"){
    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"))
  }

  get("/receivedispatch/receipts/:id") {
    val errorMessage: String = "هیچ فرم ورود کالایی با چنین شماره\u200Cای وجود ندارد."
    (DB readOnly { implicit session => sql"SELECT `submitted` FROM `forms` JOIN `received` ON `forms`.`no` = `received`.`form` WHERE `forms`.`no` = ${params("id")}".map(rs => rs.string("submitted")).single.apply })
      .getOrElse(<h1 dir="rtl" lang="fa">{errorMessage}</h1>)

  }
  get("/receivedispatch/dispatches/:id") {
    val errorMessage: String = "هیچ فرم خروج کالایی با چنین شماره\u200Cای وجود ندارد."
    (DB readOnly { implicit session => sql"SELECT `submitted` FROM `forms` JOIN `dispatched` ON `forms`.`no` = `dispatched`.`form` WHERE `forms`.`no` = ${params("id")}".map(rs => rs.string("submitted")).single.apply })
      .getOrElse(<h1 dir="rtl" lang="fa">{errorMessage}</h1>
    )
  }
  post("/receivedispatch/receipts") {
    DB localTx { implicit session =>
      SQL(ReceivingTransaction(request.body.intern).toSql).execute().apply()
    }
Ok
  }
  post("/receivedispatch/dispatches") {
    DB localTx { implicit session =>
      SQL(DispatchingTransaction(request.body.intern).toSql).execute().apply()
    }
Ok
  }
  delete("/receivedispatch/receipts/:id") {
    DB localTx { implicit session => sql"DELETE FROM forms WHERE no = ${params("id")}".update.apply()}
      Ok
  }
  delete("/receivedispatch/dispatches/:id") {
  DB localTx { implicit session => sql"DELETE FROM forms WHERE no = ${params("id")}".update.apply() }
    Ok
  }
  put("/receivedispatch/receipts/:id") {
    DB localTx { implicit session => sql"DELETE FROM forms WHERE no = ${params("id")}".update.apply() }

    DB localTx { implicit session =>
      SQL(ReceivingTransaction(request.body.intern).toSql).execute().apply()
    }
Ok
  }
  put("/receivedispatch/dispatches/:id") {
    DB localTx { implicit session => sql"DELETE FROM forms WHERE no = ${params("id")}".update.apply() }
    DB localTx { implicit session =>
      SQL(DispatchingTransaction(request.body.intern).toSql).execute().apply()
    }
Ok
  }

  get("/receivedispatch/people") {
    val people: List[Person] = DB readOnly { implicit session => sql"SELECT * FROM people".map(rs => Person(rs.string("firstname"), rs.string("surname"), rs.string("nationalidno"))).list.apply() }
    people.asJson.spaces4
  }

  get("/receivedispatch/receipts"){
    val forms: List[Int] = DB readOnly { implicit session => sql"SELECT form FROM received".map(rs => rs.int("form")).list.apply() }
      forms.asJson.spaces4
  }

  get("/receivedispatch/dispatches") {
    val forms: List[Int] = DB readOnly { implicit session => sql"SELECT form FROM dispatched".map(rs => rs.int("form")).list.apply() }
    forms.asJson.spaces4
  }

 /*
  get("/receivedispatch/currentform") {
DB readOnly { implicit session => sql"SELECT no FROM currentform".map(rs => rs.int("no")).list.apply() }
//DB localTx { implicit session => sql"SELECT @setter FROM getter".map(rs => rs.int("@setter")).execute.apply() }
}
put("/receivedispatch/currentform/:id") {
(request.body.intern).parse.toOption.asJson.(hcursor --\ "Setter").as[Json].value.get.as[Int].toOption.get
(request.body.intern).parse.toOption.asJson.(hcursor --\ "Getter").as[Json].value.get.as[Int].toOption.get

//DB localTx { implicit session => sql"SET @setter = ${params("id")}".update.apply() }
}

  */

}
