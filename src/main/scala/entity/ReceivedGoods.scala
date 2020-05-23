package entity

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write

case class ReceivedGoods(FormNo: String, ReceivedDate: String, GoodsOwner: String, BillOfLadingNo: String, PartNo: String, Origin: String, TruckNo: String) {
  implicit val formats = DefaultFormats
  def toSql: String = f"INSERT INTO ReceivedGoods (FormNo, ReceivedDate, GoodsOwner, BillOfLadingNo, PartNo, Origin, TruckNo) VALUES (, DATE '2020-02-02','', '', , '','')"
  def toJSON: String = write(this)
}
