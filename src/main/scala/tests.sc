import argonaut._, Argonaut._

val aJsonString: String = """{"No": 1234, "Date": {"Year": 2020, "Month": 2, "Day": 18}, "Owner": {"FirstName": "John", "SurName": "Doe"}}"""

val bJsonString: String =
  """{
    |  "No": 1234,
    |  "Date": {
    |    "Year": 2020,
    |    "Month": 2,
    |    "Day": 18
    |  },
    |  "Owner": {
    |    "FirstName": "John",
    |    "SurName": "Doe"
    |  }
    |}
    |""".stripMargin


//private val jsonObject: entity.form.ReceivingForm = entity.form.receivingForm(aJsonString.parse.toOption.asJson)
Parse.parse(aJsonString).toOption.get
//aJsonString.parse.toOption.asJson
bJsonString.parseOption.asJson
Parse.parse(bJsonString)
