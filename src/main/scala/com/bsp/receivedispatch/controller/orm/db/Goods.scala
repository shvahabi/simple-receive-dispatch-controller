package com.bsp.receivedispatch.controller.orm.db

case class Goods(description: String, quantity: Int, unitOfMeasurment: String, grossWeight: Float , packageWeight: Float, netWeight: Float, formNumber:Long) {
  def toSql: String =
    f"""
       |INSERT INTO `goods` (`description`, `qty`, `unit`, `gweight` , `pweight`, `nweight`, `form`)
       |VALUES('${description}', ${quantity}, '${unitOfMeasurment}', ${grossWeight}, ${packageWeight}, ${netWeight}, ${formNumber});
     """.stripMargin
}
