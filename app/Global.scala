import play.api._

import scala.sys.process._

package object globals {
  val gitCommit = Seq("git", "rev-parse", "--short", "HEAD").!!.trim
  val hostname = java.net.InetAddress.getLocalHost.getHostName
}

object Global extends GlobalSettings {

}
