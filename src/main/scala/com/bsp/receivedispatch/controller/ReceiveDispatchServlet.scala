package com.bsp.receivedispatch.controller

import com.bsp.receivedispatch.controller.orm.Transaction
import org.scalatra._
import scalikejdbc._

class ReceiveDispatchServlet extends ScalatraServlet {

  before() {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:tcp://localhost//home/shahed/Documents/bsp/builds/version 0.12.0/assets/DB/DB1", "sa", "sa")
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = false,
      singleLineMode = false
    )
  }


  get("/") {
    views.html.hello()
  }
  
	get("/forms/receiving") {

    <html charset="UTF-8">
      <p>this works</p>
      <p>{request.parameters.toMap.toString()}</p>
      <p>{params.toMap.toString()}</p>
      <p>{request.parameters.toMap.keys}</p>
      <p>{params.toMap.keys}</p>
      <p>{request.parameters.toMap.values}</p>
      <p>{params.toMap.values}</p>
      <p>{request.toString}</p>
      <p>{request.parameters.toMap.values.toList.toString()}</p>
      <p>{request.headers.get("User-Agent")}</p>
      <p>{request.headers.get("Host")}</p>
      <p>{request.headers.get("Accept")}</p>
      <p>{request.headers.get("Content-Length")}</p>
      <p>{request.headers.get("Expect")}</p>
      <p>{request.headers.get("Content-Type")}</p>
      <p>{request.body.length}</p>
      <p>{request.getContentLength}</p>
      <p>{request.body.intern}</p>
      <p>{request.body.lines.toArray}</p>
      <p>{request.body.lines.count()}</p>
      <p>{request.body.intern.linesWithSeparators}</p>
    </html>
	}

  post("/forms/receiving") {

    /*
    val jsonString = request.body
    val headers = Map("Access-Control-Allow-Origin" -> "*",
                    "Access-Control-Allow-Methods" -> "POST, GET, OPTIONS, DELETE",
                    //"Access-Control-Max-Age" -> "3600",
                    "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept")
    Ok(jsonString,headers)
     */

    /*
    <html>
      <p>{request.parameters.toMap.keys}</p>
      <!--<p>{params.toMap.keySet.toList.head}</p>-->


      <p>{params.toMap.keys.toString()}</p>
      <p>{request.parameters.toMap.values}</p>
      <p>{params.toMap.values}</p>
      <p>{request.body.intern}</p>
      <p>{request.toString}</p>

    </html>

     */

    println(request.body.intern.toString)
    /*

    <html>
      <p>{Transaction(params.toMap.keySet.toList.head).toSql}</p>
      <p>{request.toString}</p>

    </html>
*/

    /*
    DB localTx { implicit session =>
      SQL(Transaction(params.toMap.keySet.toList.head).toSql).execute().apply()
    }

     */

  }

}
