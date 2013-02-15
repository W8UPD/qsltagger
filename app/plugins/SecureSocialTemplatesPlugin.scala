package me.elrod.qsltagger

import play.api.Application
import play.api.templates.Html
import play.api.mvc._
import play.api.data.Form
import securesocial.controllers.DefaultTemplatesPlugin
import securesocial.controllers.Registration.RegistrationInfo

class SecureSocialTemplatesPlugin(application: Application) extends DefaultTemplatesPlugin(application) {
  override def getLoginPage[A](implicit request: Request[A], form: Form[(String, String)],
                               msg: Option[String] = None): Html =
  {
    views.html.login(form, msg)
  }

  override def getSignUpPage[A](implicit request: Request[A], form: Form[RegistrationInfo], token: String): Html = {
    securesocial.views.html.Registration.signUp(form, token)
  }

  override def getStartSignUpPage[A](implicit request: Request[A], form: Form[String]): Html = {
    securesocial.views.html.Registration.startSignUp(form)
  }
}
