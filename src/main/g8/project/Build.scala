import sbt._
import Keys._

import sbtrelease.ReleasePlugin._
import sbtrelease._
import ReleaseStateTransformations._
import sbtrelease.ReleasePlugin.ReleaseKeys._
import sbtbuildinfo.Plugin._
import scalariform.formatter.preferences._
import com.typesafe.sbtscalariform._

object $name$Build extends Build {

  import Dependencies._
  
  //Please add any dependencies here. Check a full list of pre created dependencies in the Dependencies or
  //type Dependencies. and get autocomplete
  val dependencies = Seq(scalaz.core, specs2, logback) 
  
  val adminConsole       = TaskKey[Unit]("console-admin", "This includes any useful admin tasks.")
  
  lazy val $name$ = Project(
    id = "$name$",
    base = file("."),
    settings = Defaults.defaultSettings 
	++ releaseSettings 
	++ net.virtualvoid.sbt.graph.Plugin.graphSettings
	++ Seq(
        releaseProcess <<= thisProjectRef apply { ref =>
          Seq[ReleaseStep](
            checkSnapshotDependencies,              // : ReleaseStep
            inquireVersions,                        // : ReleaseStep
            runTest,                                // : ReleaseStep
            setReleaseVersion,                      // : ReleaseStep
            commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
            tagRelease,                             // : ReleaseStep
            setNextVersion,                         // : ReleaseStep
            commitNextVersion,                      // : ReleaseStep
            pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
          )
        }
      )
	  ++ ScalariformPlugin.defaultScalariformSettings 
	  ++ Seq(
        ScalariformPlugin.ScalariformKeys.preferences := FormattingPreferences().
          setPreference(AlignParameters, true).
          setPreference(CompactControlReadability, true)
		  )
  ) settings (
	adminConsole <<= Defaults.consoleTask(fullClasspath in Compile,adminConsole),
	initialCommands in adminConsole := initialConsoleString,
	resolvers := Resolvers.all,
	libraryDependencies := dependencies,
  )
  
  //Change this to change your custom console for more imports. 
  //Anthing added to console will be here automatically 
  val initialConsoleString: String = """
  	println("Welcome to the utility console")
	Console.init
	import Console._
  """

}