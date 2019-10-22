package forms

import play.api.data._
import play.api.data.Forms._

case class PlayerForm(name: String)

object PlayerForm {
  val form: Form[PlayerForm] = Form(
    mapping(
      "name" -> nonEmptyText,
    )(PlayerForm.apply)(PlayerForm.unapply)
  )
}
