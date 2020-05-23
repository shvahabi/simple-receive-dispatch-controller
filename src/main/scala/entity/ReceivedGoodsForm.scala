package entity

case class ReceivedGoodsForm(goodsOwner: GoodsOwner, receivedGoods: ReceivedGoods, itemsList: List[Item]) {
  //def toSql: List[String] = List(goodsOwner.toSql, receivedGoods.toSql) :: itemsList.map(x => x.toSql)
}
