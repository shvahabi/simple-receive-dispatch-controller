import argonaut.{DecodeResult, Json, Parse}

object Main extends App {
val aJSONString: String =
    """
      {
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
 |    "Iran": 10,
 |    "TwoDigit": 22,
 |    "ThreeDigit": 792,
 |    "Alphabet": "ی"
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
 |  ]
 |}
    """.stripMargin

  case class GoodsInForm(FormNo: Int, BillOfLadingNo: String, PartNo: Int, Origin: String)

  case class GoodsOwner(FirstName: String, SurName: String, NationalIDNo: String) {
    def toSql: String = f"INSERT INTO GoodsOwner (FirstName, SurName, NationalIDNo) VALUES ('${FirstName}', '${SurName}', '${NationalIDNo}')"
  }
  case class ReceivedDate(Year:Int, Month: Int, Day: Int) {
    def toSql: String = f"INSERT INTO ReceivedDate(Year, Month, Day) VALUES ('${Year}', '${Month}', '${Day}')"
  }
  case class TruckNo(Iran: Int, TwoDigit: Int, ThreeDigit: Int, Alphabet: String) {
    def toSql: String = f"INSERT INTO TruckNo(Year, Month, Day) VALUES ('${Iran}', '${TwoDigit}', '${ThreeDigit}', '${Alphabet}')"
  }
  case class Item(Title: String, Quantity: String, UnitOfMeasurement: String, GrossWeight: String, PackageWeight: String, NetWeight: String) {
    def toSql: String = f"INSERT INTO ItemsList (Title, Quantity, Unit, GrossWeight, PackageWeight, NetWeight) VALUES ('${Title}','${Quantity}', '${UnitOfMeasurement}', '${GrossWeight}', '${PackageWeight}', '${NetWeight}')"
  }


  def decodeGoodsInFormFromJson(inJson: Json): DecodeResult[GoodsInForm] =
    for {
      formNo <- (inJson.hcursor --\ "FormNo").as[Int]
      billOfLadingNo <- (inJson.hcursor --\ "BillOfLadingNo").as[String]
      partNo <- (inJson.hcursor --\ "PartNo").as[Int]
      origin <- (inJson.hcursor --\ "Origin").as[String]
    } yield GoodsInForm(formNo, billOfLadingNo, partNo, origin)

  def decodeGoodsOwnerFromJson(inJson: Json): DecodeResult[GoodsOwner] =
    for {
      firstName <- (inJson.hcursor --\ "FirstName").as[String]
      surName <- (inJson.hcursor --\ "SurName").as[String]
      nationalIDNo <- (inJson.hcursor --\ "NationalIDNo").as[String]
    } yield GoodsOwner(firstName, surName, nationalIDNo)

  def decodeReceivedDateFromJson(inJson: Json): DecodeResult[ReceivedDate] =
    for {
      year <- (inJson.hcursor --\ "Year").as[Int]
      month <- (inJson.hcursor --\ "Month").as[Int]
      day <- (inJson.hcursor --\ "Day").as[Int]
    } yield ReceivedDate(year, month, day)

  def decodeTruckNoFromJson(inJson: Json): DecodeResult[TruckNo] =
    for {
      iran <- (inJson.hcursor --\ "Iran").as[Int]
      twoDigit <- (inJson.hcursor --\ "TwoDigit").as[Int]
      threeDigit <- (inJson.hcursor --\ "ThreeDigit").as[Int]
      alphabet <- (inJson.hcursor --\ "Alphabet").as[String]
    } yield TruckNo(iran, twoDigit, threeDigit, alphabet)

  def decodeItemFromJson(inJson: Json): DecodeResult[Item] =
    for {
      title <- (inJson.hcursor --\ "Title").as[String]
      quantity <- (inJson.hcursor --\ "Quantity").as[Int]
      unit <- (inJson.hcursor --\ "UnitOfMeasurement").as[String]
      grossWeight <- (inJson.hcursor --\ "GrossWeight").as[Int]
      packageWeight <- (inJson.hcursor --\ "PackageWeight").as[Int]
      netWeight <- (inJson.hcursor --\ "NetWeight").as[Int]
    } yield Item(title, quantity.toString, unit, grossWeight.toString, packageWeight.toString, netWeight.toString)


  case class GoodsInFormGoodsOwnerRelation(FormNo: Int, FormGoodsOwner: GoodsOwner)
  case class GoodsInFormDateRelation(FormNo: Int, FormReceivedDate: ReceivedDate)
  case class GoodsInFormTruckRelation(FormNo: Int, FormTruckNo: TruckNo)
  case class GoodsInFormItemRelation(FormNo: Int, FormItems: List[Item])


  val aJsonObject = Parse.parseOption(aJSONString).get

  val aGoodsInForm: GoodsInForm = decodeGoodsInFormFromJson(aJsonObject).value.get
  val aGoodsOwner: GoodsOwner =  decodeGoodsOwnerFromJson((aJsonObject.hcursor --\ "GoodsOwner").as[Json].value.get).value.get
  val aReceivedDate: ReceivedDate = decodeReceivedDateFromJson((aJsonObject.hcursor --\ "ReceivedDate").as[Json].value.get).value.get
  val aTruckNo: TruckNo = decodeTruckNoFromJson((aJsonObject.hcursor --\ "TruckNo").as[Json].value.get).value.get
  val itemsList: List[Item] = (aJsonObject.hcursor --\ "ItemsList").as[List[Json]].value.get.map(xxx => decodeItemFromJson(xxx).value.get)

  val aGoodsInFormGoodsOwnerRelation: GoodsInFormGoodsOwnerRelation = GoodsInFormGoodsOwnerRelation(aGoodsInForm.FormNo, aGoodsOwner)
  val aGoodsInFormDateRelation: GoodsInFormDateRelation = GoodsInFormDateRelation(aGoodsInForm.FormNo, aReceivedDate)
  val aGoodsInFormTruckRelation: GoodsInFormTruckRelation = GoodsInFormTruckRelation(aGoodsInForm.FormNo, aTruckNo)
  val aGoodsInFormItemRelation: GoodsInFormItemRelation = GoodsInFormItemRelation(aGoodsInForm.FormNo, itemsList)

  println("Form: ", aJsonObject)
  println("Owner: ", aGoodsOwner)
  println("Date: ", aReceivedDate)
  println("Truck: ", aTruckNo)
  println("Items: ", itemsList)

  println()

  println(aGoodsInFormGoodsOwnerRelation)
  println(aGoodsInFormDateRelation)
  println(aGoodsInFormTruckRelation)
  println(aGoodsInFormItemRelation)
}
