package com.bsp.receivedispatch.controller

import org.scalatra.test.scalatest._

class ReceiveDispatchServletTests extends ScalatraFunSuite {

  addServlet(classOf[ReceiveDispatchServlet], "/*")

  test("GET / on ReceiveDispatchServlet should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

}
