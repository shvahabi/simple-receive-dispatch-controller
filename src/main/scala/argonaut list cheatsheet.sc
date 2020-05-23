import argonaut._, Argonaut._

val aJsonString: String =
    """{"ItemsList":[{"title":"cream","quantity":1},{"title":"ice cream","quantity":2},{"title":"milk","quantity":3}]}"""

case class Item(title: String, quantity: Int)

implicit val codec = CodecJson.derive[Item]

val a = Item("cream", 1).asJson
val b = List(Item("cream", 1).asJson, Item("ice cream", 2).asJson, Item("milk", 3).asJson)
val c = List(Item("cream", 1), Item("ice cream", 2), Item("milk", 3))
val d = c.map(x => x.asJson)

val e = a.as[Item].value.get
val f = b.map(_.as[Item].value.get)

val g: Json = aJsonString.parse.toOption.get
val h: Json = (g.hcursor --\ "ItemsList").as[Json].value.get
val i: List[Json] = (g.hcursor --\ "ItemsList").as[List[Json]].value.get
val j: List[Item] = i.map(_.as[Item].value.get)




