package entity.form

case class ReceivingForm(goodsOwner: Person, number: Int, date: Date, partNumber: Int, truck: CarPlate, billOfLading: String, origin: String, items: List[Item], comments: String, representative: Person) extends Form