val x = List(1, 2, 3 ,4, 5)

val y = x.map(_.toString)

y.reduceLeft(_+_)
