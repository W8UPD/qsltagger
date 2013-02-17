package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import java.util.Date
import org.joda.time.{DateTime, Period}
import org.joda.time.format.{DateTimeFormat, PeriodFormat}

case class QSLCardSide(id: Long, cardID: Long, filename: String) {
  lazy val card: QSLCard = DB.withConnection { implicit c =>
    SQL("SELECT * FROM cards WHERE id={card_id}").on(
      'card_id -> cardID
    ).as(QSLCard.simple.single)
  }

  def route = controllers.routes.Assets.at("qslcards/" + filename + ".JPG")
  def thumbnailRoute = controllers.routes.Assets.at("qslcards_thumbs/" + filename + ".JPG")

}

object QSLCardSide {
  val simple = {
    get[Long]("id") ~
    get[Long]("card_sides.card_id") ~
    get[String]("card_sides.filename") map {
      case id ~ cardID ~ filename => QSLCardSide(id, cardID, filename)
    }
  }

  lazy val allSides = DB.withConnection { implicit c =>
    SQL("SELECT t1.* FROM card_sides t1 JOIN (SELECT MAX(id) AS id, card_id, filename FROM card_sides GROUP BY card_id) t2 ON t1.id = t2.id").as(QSLCardSide.simple *)
  }
}