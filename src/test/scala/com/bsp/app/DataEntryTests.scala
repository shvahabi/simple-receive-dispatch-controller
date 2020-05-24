package com.bsp.app

import org.scalatra.test.scalatest._

class DataEntryTests extends ScalatraFunSuite {

  addServlet(classOf[DataEntry], "/*")

  test("GET / on DataEntry should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

}
