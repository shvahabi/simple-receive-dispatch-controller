import argonaut.{DecodeResult, Json, Parse}
//import entity.Item
//import io.circe.Json
//import io.circe.parser._

//import net.liftweb.json._

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

  /*
  val itemInString: String =
    """
      |{
      |"Title": "شیر",
      |"Quantity": 2,
      |"UnitOfMeasurement": "باکس",
      |"GrossWeight": 55,
      |"PackageWeight": 2,
      |"NetWeight": 53
      |}
    """.stripMargin
   */

  case class Item(Title: String, Quantity: String, UnitOfMeasurement: String, GrossWeight: String, PackageWeight: String, NetWeight: String)

  //val itemInJson: Json = Parse.parseOption(itemInString).get

  def decodeItemFromJson(inJson: Json): DecodeResult[Item] =
    for {
      title <- (inJson.hcursor --\ "Title").as[String]
      quantity <- (inJson.hcursor --\ "Quantity").as[Int]
      unit <- (inJson.hcursor --\ "UnitOfMeasurement").as[String]
      grossWeight <- (inJson.hcursor --\ "GrossWeight").as[Int]
      packageWeight <- (inJson.hcursor --\ "PackageWeight").as[Int]
      netWeight <- (inJson.hcursor --\ "NetWeight").as[Int]
    } yield Item(title, quantity.toString, unit, grossWeight.toString, packageWeight.toString, netWeight.toString)

  //val anItem: Item = decodeItemFromJson(itemInJson).value.get

  /*
  val goodsOwnerInString: String =
    """
      {
      "FirstName": "شاهد",
      "SurName": "وهابی",
      "NationalIDNo": "0061234567"
      }
    """.stripMargin
   */

  case class GoodsOwner (FirstName: String, SurName: String, NationalIDNo: String)

  //val goodsOwnerInJson: Json = Parse.parseOption(goodsOwnerInString).get

  def decodeGoodsOwnerFromJson(inJson: Json): DecodeResult[GoodsOwner] =
    for {
      firstName <- (inJson.hcursor --\ "FirstName").as[String]
      surName <- (inJson.hcursor --\ "SurName").as[String]
      nationalIDNo <- (inJson.hcursor --\ "NationalIDNo").as[String]
    } yield GoodsOwner (firstName, surName, nationalIDNo)

  //val anOwner: GoodsOwner = decodeGoodsOwnerFromJson(goodsOwnerInJson).value.get

  /*
  val aDateInString: String =
    """
      {
      "Year": 2020,
      "Month": 2,
      "Day": 31
      }
    """.stripMargin
   */

  case class ReceivedDate(Year:Int, Month: Int, Day: Int)

  //val receivedDateInJson: Json = Parse.parseOption(aDateInString).get

  def decodeReceivedDateFromJson(inJson: Json): DecodeResult[ReceivedDate] =
    for {
      year <- (inJson.hcursor --\ "Year").as[Int]
      month <- (inJson.hcursor --\ "Month").as[Int]
      day <- (inJson.hcursor --\ "Day").as[Int]
    } yield ReceivedDate(year, month, day)
  //val aDate: ReceivedDate = decodeReceivedDateFromJson(receivedDateInJson).value.get

  //println(aDate.Month + "/" + aDate.Day + "/" + aDate.Year)

  /*
  val aTruckNoInString: String =
    """
      |{
      |    "Iran": 10,
      |    "TwoDigit": 22,
      |    "ThreeDigit": 792,
      |    "Alphabet": "ی"
      |  }
    """.stripMargin
   */

  case class TruckNo(Iran: Int, TwoDigit: Int, ThreeDigit: Int, Alphabet: String)

  //val truckNoInJson: Json = Parse.parseOption(aTruckNoInString).get

  def decodeTruckNoFromJson(inJson: Json): DecodeResult[TruckNo] = {
    for {
      iran <- (inJson.hcursor --\ "Iran").as[Int]
      twoDigit <- (inJson.hcursor --\ "TwoDigit").as[Int]
      threeDigit <- (inJson.hcursor --\ "ThreeDigit").as[Int]
      alphabet <- (inJson.hcursor --\ "Alphabet").as[String]
    } yield TruckNo(iran, twoDigit, threeDigit, alphabet)
  }

  //val aTruckNo: TruckNo = decodeTruckNoFromJson(truckNoInJson).value.get
  /*print("Iran" + aTruckNo.Iran + " " + aTruckNo.TwoDigit)
  print(" " + aTruckNo.Alphabet + " ")
  print(aTruckNo.ThreeDigit)*/

  case class GoodsInFormTruckRelation(FormNo: Int, FormTruckNo: TruckNo)

  def decodeGoodsInFormTruckRelation(inJson: Json): DecodeResult[GoodsInFormTruckRelation] =
    for {
      formNo <- (inJson.hcursor --\ "FormNo").as[Int]
      formTruckNo <- decodeTruckNoFromJson((inJson.hcursor --\ "TruckNo").as[Json].value.get)
    } yield GoodsInFormTruckRelation(formNo, formTruckNo)

  //*****val aRelation: GoodsInFormTruckRelation = decodeGoodsInFormTruckRelation(Parse.parseOption(aJSONString).get).value.get


  case class GoodsInFormItemRelation(FormNo: Int, FormItems: List[Item])




  def decodeGoodsInFormItemRelation(inJson: Json, formItems: List[Item]): DecodeResult[GoodsInFormItemRelation] = {
    for {
      formNo <- (inJson.hcursor --\ "FormNo").as[Int]
    } yield GoodsInFormItemRelation(formNo, formItems)
  }

  val inJson = Parse.parseOption(aJSONString).get
  val itemsList: List[Item] = (inJson.hcursor --\ "ItemsList").as[List[Json]].value.get.map(sd => decodeItemFromJson(sd).value.get)


  println(    ((inJson.hcursor --\ "ItemsList").as[List[Json]].value.get).map(sd => decodeItemFromJson(sd).value.get) )
  println(    decodeItemFromJson((inJson.hcursor --\ "ItemsList").as[List[Json]].value.get.head).value.get)


  case class GoodsInFormDateRelation(FormNo: Int, FormReceivedDate: ReceivedDate)

  def decodeGoodsInFormDateRelation(inJson: Json): DecodeResult[GoodsInFormDateRelation] =
    for {
      formNo <- (inJson.hcursor --\ "FormNo").as[Int]
      formReceivedDate <- decodeReceivedDateFromJson((inJson.hcursor --\ "ReceivedDate").as[Json].value.get)
    } yield GoodsInFormDateRelation(formNo, formReceivedDate)


  case class GoodsInFormGoodsOwnerRelation(FormNo: Int, FormGoodsOwner: GoodsOwner)

  def decodeGoodsInFormGoodsOwnerRelation(inJson: Json): DecodeResult[GoodsInFormGoodsOwnerRelation] =
    for {
      formNo <- (inJson.hcursor --\ "FormNo").as[Int]
      formGoodsOwner <- decodeGoodsOwnerFromJson((inJson.hcursor --\ "GoodsOwner").as[Json].value.get)
    } yield GoodsInFormGoodsOwnerRelation(formNo, formGoodsOwner)

  case class GoodsInForm(FormNo: Int, BillOfLadingNo: String, PartNo: Int, Origin: String)

  def decodeGoodsInFormFromJson(inJson: Json): DecodeResult[GoodsInForm] = {
    for {
      formNo <- (inJson.hcursor --\ "FormNo").as[Int]
      billOfLadingNo <- (inJson.hcursor --\ "BillOfLadingNo").as[String]
      partNo <- (inJson.hcursor --\ "PartNo").as[Int]
      origin <- (inJson.hcursor --\ "Origin").as[String]
    } yield GoodsInForm(formNo, billOfLadingNo, partNo, origin)
  }




}
