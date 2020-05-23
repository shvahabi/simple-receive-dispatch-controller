import argonaut._, Argonaut._

case class Person(name: String, age: Int)

// Object to Json conversion
implicit def PersonEncodeToJson: EncodeJson[Person] =
  jencode2L((p: Person) => (p.name, p.age))("name", "age")

val a = Person("Shahed", 38).asJson //PREFERRED
val b = PersonEncodeToJson(Person("Shahed", 38))

// Json to Object Conversion
implicit def PersonDecodeFromJson: DecodeJson[Person] =
  jdecode2L(Person.apply)("name", "age")

val c = a.as[Person].value.get
val d = b.as[Person].toOption.get //PREFERRED

def ExplicitPersonCodecJson: CodecJson[Person] =
  codec2(Person.apply, (Person.unapply _) andThen (_.get))("name", "age")

val e = ExplicitPersonCodecJson(Person("Shahed", 38))
val f = ExplicitPersonCodecJson(a.hcursor).value.get


//String to Json conversion
val vJsonString = """{"name": "Shahed", "age": 25}"""
val wJsonString = """{"name": "Shahed", "age": 35}"""

val t = Parse.parse(vJsonString).toOption.get
val u = wJsonString.parseOption.get

val z = JsonParser.parse(vJsonString).toOption.get
val y = JsonParser.parse(wJsonString).toOption.asJson
val m = wJsonString.parse.toOption.asJson //PREFERRED

//Json to String conversion
a.toString
b.toString
e.toString
t.toString //PREFERRED
u.toString
z.toString
y.toString

//nested Json by hcursor
val o = """{"name": "Shahed", "age": 38, "cloths": {"coat": "green", "hat": "blue", "shoes": "black"}}"""
(o.parseOption.get.hcursor --\ "cloths").as[Json].value.get

/*
//several other variants
val e = PersonDecodeFromJson(a.hcursor).value.asJson
val f = PersonDecodeFromJson(b.hcursor).value.get
val g = PersonDecodeFromJson(c.hcursor).value.get

val h = ExplicitPersonCodecJson(a.hcursor).value.get
val i = ExplicitPersonCodecJson(b.hcursor).value.get
val j = ExplicitPersonCodecJson(c.hcursor).value.get*/