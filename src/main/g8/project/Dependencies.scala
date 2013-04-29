import sbt._

object Resolvers {

  val javaNet = JavaNet1Repository
  val mavenLocal = "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
  val sunrepo = "Sun Maven2 Repo" at "http://download.java.net/maven/2"
  val databinder = "DataBinder" at "http://databinder.net/repo"

  //For scala releases
  val typeSafeRelease = "TypeSafe Release" at "http://repo.typesafe.com/typesafe/releases/"
  val typeSafeSnapshots = "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

  val scalatoolsSnapshots = "Scalatools Snapshots" at "http://scala-tools.org/repo-snapshots"
  val sonatypeSnapshots = "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"
  val akkaSnapshots = "Akka Snapshots" at "http://repo.akka.io/snapshots/"

  val sonatypeReleases = "releases" at "http://oss.sonatype.org/content/repositories/releases"

  val sportaneousResolvers =
    Seq(typeSafeRelease, typeSafeSnapshots, javaNet, mavenLocal, sunrepo,
      scalatoolsSnapshots, sonatypeSnapshots, sonatypeReleases, akkaSnapshots)
}

object Dependencies {

  def crossMapped(mappings: (String, String)*): CrossVersion =
    CrossVersion.binaryMapped(Map(mappings: _*) orElse {
      case v => v
    })

  lazy val CVMapping10010 = crossMapped("2.10.0" -> "2.10")

  /** Versions **/
  val jettyVersion = "6.1.22"
  val akkaVersion = "2.2-M2"

  val liftVersion = "2.5-RC5" //TODO Change this
  val rogueVersion = "2.0.0-beta22"
  val dispatchVersion = "0.8.9"
  val rebootVersion = "0.10.0"

  /** Jetty & Friends **/

  val jetty6 = "org.mortbay.jetty" % "jetty" % jettyVersion % "container"
  val jetty6Test = "org.mortbay.jetty" % "jetty" % jettyVersion % "test"
  val jetty7 = "org.eclipse.jetty" % "jetty-webapp" % "7.3.0.v20110203" % "jetty"
  val jetty7security = "org.eclipse.jetty" % "jetty-security" % "7.3.0.v20110203"

  /** Logging **/

  val slf4j    = "org.slf4j" % "slf4j-log4j12" % "1.6.1" % "test"
  val slf4jNon = "org.slf4j" % "slf4j-api" % "1.6.1"
  val logback  = "ch.qos.logback" % "logback-classic" % "0.9.30"
  val logLady  = "org.eintr.loglady" %% "loglady" % "1.0.0"

  /** Akka & Friends **/

  def akka(name: String, v: String = akkaVersion) =
    "com.typesafe.akka" %% ("akka-%s" format name) % v withSources() cross CVMapping10010

  def akkaDeps(v: String = akkaVersion) = Seq(akka("actor",v), akka("kernel", v), akka("slf4j", v))

  /** Lift **/

  def lift(name: String, v: String = liftVersion) =
    "net.liftweb" %% ("lift-%s" format name) % v withSources() exclude("joda-time", "joda-time") cross CVMapping10010

  def liftDeps(v: String = liftVersion) =
    Seq(lift("webkit",v), lift("mapper",v),  lift("record",v), lift("wizard",v), lift("json",v),
       lift("mongodb",v), lift("mongodb-record",v))

  val lift24Lib = liftDeps(liftVersion)

  /** Rogue **/

  def rogue(name: String, v: String = rogueVersion) =
    "com.foursquare" %% ("rogue-%s" format name) % v intransitive() withSources() cross CVMapping10010

  val rogueLibs  = Seq(rogue("field"), rogue("core"), rogue("lift"))

  /** Scala utils (just include in distro) **/

  val classutil = "org.clapper" %% "classutil" % "1.0.1" cross CVMapping10010

  val scalaTimeLib = "com.github.nscala-time" %% "nscala-time" % "0.2.0"

  /** Dispatch & Friends **/

  def databinder(name: String, v: String = dispatchVersion) =
    "net.databinder" %% ("dispatch-" + name) % v withSources() cross CVMapping10010

  def reboot(name: String, v: String = rebootVersion) =
    "net.databinder.dispatch" %% ("dispatch-" + name) % v cross CVMapping10010

  val dispatchClassicLibs: Seq[sbt.ModuleID]  =
    Seq(databinder("http"), databinder("oauth"), databinder("json"), databinder("mime"), databinder("http-json"))

  val rebootLibs: Seq[sbt.ModuleID]  = Seq(reboot("core"), reboot("json4s-native"))

  val ning = "com.ning" % "async-http-client" % "1.7.5"

  val dispatchLibs = dispatchClassicLibs ++  Seq(ning)

  /** Java Libraries  **/

  val commonLang = "commons-lang" % "commons-lang" % "2.5" withSources()

  val jbCrypt = "org.mindrot" % "jbcrypt" % "0.3m" % "compile" withSources()

  val csvWriterScala = "com.github.tototoshi" % "scala-csv_2.9.1" % "0.3"

  val amazon = "com.amazonaws" % "aws-java-sdk" % "1.3.30"

  /** Language extensions **/

  val scalaz = "org.scalaz" %% "scalaz-core" % "7.0.0" withSources() cross CVMapping10010

  /** Testing **/

  val specsDeps = Seq(
    "org.specs2" %% "specs2" % "1.14.1-SNAPSHOT" % "test" cross CVMapping10010
  )

  val servlet = "javax.servlet" % "servlet-api" % "2.5"
  val junit = "junit" % "junit" % "4.5"

}
