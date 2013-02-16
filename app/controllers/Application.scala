package controllers

import play.api._
import play.api.mvc._

import securesocial.core.{SocialUser, Authorization}

import models._

object Application extends Controller with securesocial.core.SecureSocial {

  def index = SecuredAction { implicit request =>
    if (!SocialUserObject.hasLoggedInBefore(request.user.id)) {
      Redirect(routes.Application.tutorial)
    } else {
      val card = QSLCard.getRandom()
      Ok(views.html.tagCard(request.user, card))
    }
  }

  def tutorial = SecuredAction { implicit request =>
    if (!SocialUserObject.hasLoggedInBefore(request.user.id)) {
      SocialUserObject.setLogin(request.user.id)
    }
    Ok(views.html.tutorial(request.user))
  }

  def searchdemo = SecuredAction { implicit request =>
    Ok(views.html.searchdemo(request.user))
  }

  def leaderboarddemo = SecuredAction { implicit request =>
    Ok(views.html.leaderboarddemo(request.user))
  }

  def search = UserAwareAction { implicit request =>
    Ok(views.html.search(request.user))
  }

  def gallery = UserAwareAction { implicit request =>
    Ok(views.html.gallery(request.user))
  }

  def galleryIncremental(start: Int = 0) = Action { implicit request =>
    val sides = QSLCardSide.allSides.grouped(40).toList
    if (start > sides.length - 1) {
      Ok(views.html.galleryIncremental(List()))
    } else {
      Ok(views.html.galleryIncremental(sides(start)))
    }
  }

}
