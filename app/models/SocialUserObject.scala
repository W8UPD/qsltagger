package models

import securesocial.core.UserId

import anorm._
import play.api.db._
import play.api.Play.current
import play.api.Application

object SocialUserObject {
  def hasLoggedInBefore(id: UserId): Boolean = DB.withConnection { implicit c =>
    val everLoggedIn = SQL(
      "SELECT ever_logged_in FROM users WHERE user_id = {user_id} and provider = {provider}").on(
      'user_id -> id.id,
      'provider -> id.providerId
    )().head
    everLoggedIn[Boolean]("ever_logged_in")
  }

  def setLogin(id: UserId) = DB.withConnection { implicit c =>
    val loginUpdate = SQL(
      "UPDATE users SET ever_logged_in = true WHERE user_id = {user_id} and provider = {provider}").on(
      'user_id -> id.id,
      'provider -> id.providerId
    ).executeUpdate()
  }
}
