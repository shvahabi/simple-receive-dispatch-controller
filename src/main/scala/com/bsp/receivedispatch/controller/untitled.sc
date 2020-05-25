import scalikejdbc._


Class.forName("org.h2.Driver")
ConnectionPool.singleton("jdbc:h2:tcp://localhost//home/shahed/Documents/bsp/builds/controller/0.30.0/assets/DB/DB6", "sa", "sa")
GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
  enabled = false,
  singleLineMode = false
)

DB readOnly { implicit session => sql"SELECT @currentform".map(rs => rs).single.apply().getOrElse(""). }