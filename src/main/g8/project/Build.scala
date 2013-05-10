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
  ) setting (
	libraryDependencies := dependencies,
	resolvers := Resolvers.all
  )

}