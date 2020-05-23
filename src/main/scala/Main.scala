import argonaut.Argonaut._
import argonaut.Json
import entity.Transaction
import entity.form._


object Main extends App {
val aJSONString: String =
  """{
    |  "FormNo": 1234,
    |  "ReceivedDate": {
    |    "Year": 2020,
    |    "Month": 2,
    |    "Day": 18
    |  },
    |  "GoodsOwner": {
    |    "FirstName": "Shahed",
    |    "SurName": "Vahabi",
    |    "NationalIDNo": "0061234567"
    |  },
    |  "BillOfLadingNo": "GE-12-1398-S",
    |  "PartNo": 5,
    |  "Origin": "Bandar Abbas",
    |  "TruckNo": {
    |    "State": 10,
    |    "Serial": 22,
    |    "Area": "yy",
    |    "Random": 792
    |  },
    |  "ItemsList": [
    |    {
    |      "Description": "cream",
    |      "Quantity": 2,
    |      "UnitOfMeasurement": "box",
    |      "GrossWeight": 100.123456,
    |      "PackageWeight": 2.234567,
    |      "NetWeight": 98.345678
    |    },
    |    {
    |      "Description": "ice cream",
    |      "Quantity": 3,
    |      "UnitOfMeasurement": "pieces",
    |      "GrossWeight": 150.9011,
    |      "PackageWeight": 1.5,
    |      "NetWeight": 149.4467
    |    },
    |    {
    |      "Description": "milk",
    |      "Quantity": 2,
    |      "UnitOfMeasurement": "Gallon",
    |      "GrossWeight": 55,
    |      "PackageWeight": 2,
    |      "NetWeight": 53
    |    }
    |  ],
    |  "Comments": "All in good conditions",
    |  "Representative": {
    |    "FirstName": "Hamed",
    |    "SurName": "Akhavan",
    |    "NationalIDNo": "4569519058"
    |  }
    |}
  """.stripMargin


  //val myJson = aJSONString.parse.toOption.asJson
  //val myForm = receivingForm(myJson)

  //val carplate = (myJson.hcursor --\ "TruckNo").as[Json].value.get.as[CarPlate].toOption.get

  /*
  println(entity.orm.Person(myForm.goodsOwner.firstName, myForm.goodsOwner.surName, myForm.goodsOwner.nationalIDNo).toSql)
  println(entity.orm.Person(myForm.representative.firstName,myForm.representative.surName, myForm.representative.nationalIDNo).toSql)
  println(entity.orm.ReceivingForm(myForm.origin, myForm.billOfLading, myForm.representative.nationalIDNo, myForm.number).toSql)
  println(entity.orm.Form(myForm.number, myForm.comments, myForm.number , myForm.partNumber, aJSONString, myForm.goodsOwner.nationalIDNo, myForm.date.concat, myForm.truck.concat).toSql)

   */

  print(Transaction(aJSONString).toSql)
//println(entity.form.person(myJson))
}
