package com.bsp.control

import org.scalatra.test.scalatest._

class SimpleDataEntryTests extends ScalatraFunSuite {

  addServlet(classOf[SimpleDataEntry], "/*")

  test("GET / on SimpleDataEntry should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

}
