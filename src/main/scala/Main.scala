import argonaut.Argonaut._
import entity.form._


object Main extends App {
val aJSONString: String =
  """
    |{
    |  "FormNo": 1234,
    |  "ReceivedDate": {
    |    "Year": 2020,
    |    "Month": 2,
    |    "Day": 31
    |  },
    |  "GoodsOwner": {
    |    "FirstName": "شاهد",
    |    "SurName": "وهابی",
    |    "NationalIDNo": "0061234567"
    |  },
    |  "BillOfLadingNo": "GE-12-1398-S",
    |  "PartNo": 5,
    |  "Origin": "بندر عباس",
    |  "TruckNo": {
    |    "State": 10,
    |    "Serial": 22,
    |    "Area": "ی",
    |    "Random": 792
    |  },
    |  "ItemsList": [
    |    {
    |      "Title": "خامه",
    |      "Quantity": 2,
    |      "UnitOfMeasurement": "کارتن",
    |      "GrossWeight": 100,
    |      "PackageWeight": 2,
    |      "NetWeight": 98
    |    },
    |    {
    |      "Title": "بستنی",
    |      "Quantity": 3,
    |      "UnitOfMeasurement": "عدد",
    |      "GrossWeight": 150,
    |      "PackageWeight": 1,
    |      "NetWeight": 149
    |    },
    |    {
    |      "Title": "شیر",
    |      "Quantity": 2,
    |      "UnitOfMeasurement": "گالن",
    |      "GrossWeight": 55,
    |      "PackageWeight": 2,
    |      "NetWeight": 53
    |    }
    |  ],
    |  "Comments": "تمامی کالاها در وضعیت مطلوب می باشند",
    |  "Representative": {
    |    "FirstName": "شاهد",
    |    "SurName": "وهابی",
    |    "NationalIDNo": "0061234567"
    |  }
    |}
  """.stripMargin

  val myForm = receivingForm(aJSONString.parse.toOption.asJson)

  println(myForm.date)
  println(myForm.number)
  println(myForm.goodsOwner)
  println(myForm.truck)
  println(myForm.billOfLading)
  println(myForm.origin)
  println(myForm.partNumber)
  println(myForm.comments)
  println(myForm.representative)
}
