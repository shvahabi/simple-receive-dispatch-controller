package com.bsp.receivedispatch.controller.orm.db

case class DispatchingForm(draftNumber: Long, nidOfBeneficiary: String, nidOfAsignee: String, draftDate: Int, formNumber: Long) {
  def toSql: String =
    f"""
       |INSERT INTO
       |`dispatched` (`draft number`, `beneficiary`, `assignee`, `draft date`, `form`)
       |VALUES (${draftNumber}, ${nidOfBeneficiary}, ${nidOfAsignee}, ${draftDate}, ${formNumber});
     """.stripMargin
}
