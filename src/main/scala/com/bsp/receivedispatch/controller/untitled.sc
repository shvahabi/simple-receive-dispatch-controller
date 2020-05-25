import scalikejdbc._


Class.forName("org.h2.Driver")
ConnectionPool.singleton("jdbc:h2:tcp://localhost//home/shahed/Documents/bsp/builds/controller/0.17.0/assets/DB/DB3", "sa", "sa")
GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
  enabled = false,
  singleLineMode = false
)

case class me(name: String, lastname: String, nid: String)



a