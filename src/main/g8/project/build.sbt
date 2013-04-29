
resolvers ++= Seq (
    "zentrope" at "http://zentrope.com/maven",
    "Web plugin repo" at "http://siasia.github.com/maven2",
    "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
   "sbt-idea-repo" at "http://mpeltonen.github.com/maven/",
   Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-snapshots"))(Resolver.ivyStylePatterns),
   ("repo.codahale.com" at "http://repo.codahale.com")
)

// sbt-release
addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.7")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.1")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.4.0")

addSbtPlugin("com.typesafe.sbtscalariform" % "sbtscalariform" % "0.5.1")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.2.2")