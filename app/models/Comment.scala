package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import java.util.Date
import org.joda.time.{DateTime, Period}
import org.joda.time.format.{DateTimeFormat, PeriodFormat}

case class Comment(id: Long, cardID: Long, comment: String, date: Date)

object Comment {
  val simple = {
    get[Long]("id") ~
    get[Long]("card_id") ~
    get[String]("comment") ~
    get[Date]("commented_at") map {
      case id ~ cardID ~ comment ~ date => Comment(id, cardID, comment, date)
    }
  }

  val withUsers = (Comment.simple) ~ (QSLTaggerUserService.simple) map {
    case comment ~ user => (comment, user)
  }

  def getForCardID(cardID: Long) = DB.withConnection { implicit c =>
    SQL("select a.*, b.* from card_comments a, users b where a.card_id={card_id} and b.id=a.user_id").on(
      'card_id -> cardID
    ).as(Comment.withUsers *)
  }
}
