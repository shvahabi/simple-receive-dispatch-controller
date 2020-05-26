package com.bsp.receivedispatch.controller.orm.db

case class DispatchingForm(draftDate: Int, draftNumber: String, nidOfBeneficiary: String, nidOfAssignee: String) {
  def toSql(formNumber: Long): String =
    f"""
       |INSERT INTO
       |`dispatched` (`draft number`, `beneficiary`, `assignee`, `draft date`, `form`)
       |VALUES ('${draftNumber}', '${nidOfBeneficiary}', '${nidOfAssignee}', ${draftDate}, ${formNumber});
     """.stripMargin
}
