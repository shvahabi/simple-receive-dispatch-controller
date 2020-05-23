package entity

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write

case class GoodsOwner (FirstName: String, SurName: String, NationalIDNo: String) {
  implicit val formats = DefaultFormats
  def toSql: String = f"INSERT INTO GoodsOwner (FirstName, SurName, NationalIDNo) VALUES ('${FirstName}', '${SurName}', '${NationalIDNo}')"
  def toJSON: String = write(this)
}
