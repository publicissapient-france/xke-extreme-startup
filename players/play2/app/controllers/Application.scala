package controllers

import scala.annotation.implicitNotFound

import play.api.mvc.Action
import play.api.mvc.Controller

object Application extends Controller {

  def index = Action { implicit request =>
    request.queryString.get("q") match {
      case Some(Seq("sayhello")) => Ok("hello")
      case Some(q) => BadRequest("unknown query: " + q.reduce(_ + ", " + _))
      case None => BadRequest("no query received")
    }
  }
}