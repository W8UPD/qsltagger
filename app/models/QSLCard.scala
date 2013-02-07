package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import java.util.Date
import org.joda.time.{DateTime, Period}
import org.joda.time.format.{DateTimeFormat, PeriodFormat}

case class QSLCardSide(id: Long, filename: String)
case class QSLCard(id: Long, approved: Boolean) {
  lazy val sides: List[QSLCardSide] = DB.withConnection { implicit c =>
    SQL("SELECT * FROM card_sides WHERE card_id={card_id}").on(
      'card_id -> id
    ).as(QSLCardSide.simple *)
  }
}

object QSLCard {
  val simple = {
    get[Long]("id") ~
    get[Boolean]("approved") map {
      case id ~ approved => QSLCard(id, approved)
    }
  }

  def getRandom() = DB.withConnection { implicit c =>
    SQL("SELECT * FROM cards ORDER BY RAND() LIMIT 1").as(QSLCard.simple.single)
  }
}

object QSLCardSide {
  val simple = {
    get[Long]("id") ~
    get[Long]("card_id") ~
    get[String]("filename") map {
      case id ~ card_id ~ filename => QSLCardSide(id, filename)
    }
  }
}
