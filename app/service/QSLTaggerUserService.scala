package me.elrod.qsltagger

import securesocial.core.{AuthenticationMethod, Identity, UserId, UserServicePlugin, SocialUser}
import securesocial.core.providers.Token

import anorm._
import play.api.db._
import play.api.Play.current
import play.api.Application

import java.sql.Timestamp

class QSLTaggerUserService(application: Application) extends UserServicePlugin(application) {
  /** Finds a SocialUser that maches the specified id
    *
    * @param id the user id
    * @return an optional user
    */
  def find(id: UserId): Option[Identity] = DB.withConnection { implicit c =>
    val users = SQL("SELECT * FROM users WHERE user_id = {user_id}").on(
      'user_id -> id.id
    )().map { row =>
      SocialUser(
        UserId(row[String]("user_id"), row[String]("provider")),
        row[String]("first_name"),
        row[String]("last_name"),
        row[String]("full_name"),
        row[Option[String]]("email"),
        row[Option[String]]("profile_pic"),
        AuthenticationMethod(row[String]("auth_method"))
      )
    }.toList

    if (users.size == 1) {
      Some(users(0))
    } else {
      None
    }
  }


  def findByEmailAndProvider(email: String, providerId: String): Option[Identity] = { None }

  /** Saves the user.
    *
    * This method gets called when a user logs in.
    * This is your chance to save the user information in your backing store.
    *
    * @param user
    */
  def save(user: Identity): Identity = {
    DB.withConnection { implicit c =>
      if (find(user.id) != None) {
        SQL(
          """UPDATE users SET
          provider={provider},
          first_name={first_name},
          last_name={last_name},
          full_name={full_name},
          email={email},
          profile_pic={profile_pic},
          auth_method={auth_method}
          WHERE user_id = {user_id}""").on(
          'provider -> user.id.providerId,
          'first_name -> user.firstName,
          'last_name -> user.lastName,
          'full_name -> user.fullName,
          'email -> user.email,
          'profile_pic -> user.avatarUrl,
          'auth_method -> user.authMethod.method,
          'user_id -> user.id.id).executeUpdate()
      } else {
        SQL(
          """INSERT INTO users (
            user_id,
            provider,
            first_name,
            last_name,
            full_name,
            email,
            profile_pic,
            auth_method
          ) VALUES (
            {user_id},
            {provider},
            {first_name},
            {last_name},
            {full_name},
            {email},
            {profile_pic},
            {auth_method}
          )""").on(
            'user_id -> user.id.id,
            'provider -> user.id.providerId,
            'first_name -> user.firstName,
            'last_name -> user.lastName,
            'full_name -> user.fullName,
            'email -> user.email,
            'profile_pic -> user.avatarUrl,
            'auth_method -> user.authMethod.method).executeUpdate()
      }
      user
    }
  }

  def save(token: Token) = { }

  def findToken(token: String): Option[Token] = { None }

  def deleteToken(uuid: String) { }

  def deleteExpiredTokens() { }
}
