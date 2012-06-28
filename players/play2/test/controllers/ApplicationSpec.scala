import org.specs2.mutable.Specification

import play.api.test.Helpers.BAD_REQUEST
import play.api.test.Helpers.GET
import play.api.test.Helpers.OK
import play.api.test.Helpers.charset
import play.api.test.Helpers.contentAsString
import play.api.test.Helpers.contentType
import play.api.test.Helpers.routeAndCall
import play.api.test.Helpers.status
import play.api.test.FakeRequest

class ApplicationSpec extends Specification {

  "The application" should {
    "complain when receiving no query" in {
      val Some(result) = routeAndCall(FakeRequest(GET, "/"))

      status(result) must equalTo(BAD_REQUEST)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      contentAsString(result) must contain("no query received")
    }

    "complain when receiving unknown query" in {
      val Some(result) = routeAndCall(FakeRequest(GET, "/?q=uNkNoWn"))

      status(result) must equalTo(BAD_REQUEST)
      contentAsString(result) must contain("unknown query: uNkNoWn")
    }

    "complain when receiving more than one query" in {
      val Some(result) = routeAndCall(FakeRequest(GET, "/?q=que&q=ry"))

      status(result) must equalTo(BAD_REQUEST)
      contentAsString(result) must contain("unknown query: que, ry")
    }

    "say hello" in {
      val Some(result) = routeAndCall(FakeRequest(GET, "/?q=sayhello"))

      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      contentAsString(result) must contain("hello")
    }
  }
}