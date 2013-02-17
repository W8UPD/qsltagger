package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import java.util.Date
import org.joda.time.{DateTime, Period}
import org.joda.time.format.{DateTimeFormat, PeriodFormat}

case class QSLCard(id: Long, approved: Boolean) {
  lazy val sides: List[QSLCardSide] = DB.withConnection { implicit c =>
    SQL("SELECT * FROM card_sides WHERE card_id={card_id}").on(
      'card_id -> id
    ).as(QSLCardSide.simple *)
  }

  lazy val comments = Comment.getForCardID(id)
}

object QSLCard {
  val simple = {
    get[Long]("cards.id") ~
    get[Boolean]("cards.approved") map {
      case id ~ approved => QSLCard(id, approved)
    }
  }

  val withSides = (QSLCard.simple) ~ (QSLCardSide.simple) map {
    case card ~ sides => (card, sides)
  }

  def getRandom() = DB.withConnection { implicit c =>
    SQL("SELECT * FROM cards ORDER BY RAND() LIMIT 1").as(QSLCard.simple.single)
  }

  def getAll() = DB.withConnection { implicit c =>
    SQL("SELECT * FROM cards LEFT JOIN card_sides ON cards.id = card_sides.card_id")
      .as(QSLCard.withSides *)
  }

  def getFromID(id: Long) = DB.withConnection { implicit c =>
    SQL("SELECT * FROM cards WHERE id={id}").on(
      'id -> id
    ).as(QSLCard.simple.single)
  }
}
