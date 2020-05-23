package entity

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write

case class Item(Title: String, Quantity: String, Unit: String, GrossWeight: String, PackageWeight: String, NetWeight: String, ReceivedGoods: String) {
  implicit val formats = DefaultFormats
  def toSql: String = f"INSERT INTO ItemsList (Title, Quantity, Unit, GrossWeight, PackageWeight, NetWeight, ReceivedGoods) VALUES ('','', '', '', '', '','' )"
  def toJSON: String = write(this)
}
