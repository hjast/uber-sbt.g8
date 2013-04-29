import sbt._
import Keys._


// sbt-release plugin
import sbtrelease.ReleasePlugin._
import sbtrelease._
import ReleaseStateTransformations._
import sbtrelease.ReleasePlugin.ReleaseKeys._
import sbtbuildinfo.Plugin._
import scalariform.formatter.preferences._
import com.typesafe.sbtscalariform._

object $name$Build extends Build {

  import Dependencies._
  
  val depends = Seq(scalaz, specsDeps) 
  
  lazy val $name$ = Project(
    id = "$name$",
    base = file("."),
    settings = Defaults.defaultSettings 
	++ releaseSettings 
	++ Seq(

        releaseProcess <<= thisProjectRef apply { ref =>
          Seq[ReleaseStep](
            checkSnapshotDependencies,              // : ReleaseStep
            inquireVersions,                        // : ReleaseStep
            runTest,                                // : ReleaseStep
            setReleaseVersion,                      // : ReleaseStep
            commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
            tagRelease,                             // : ReleaseStep
            publishArtifacts,                       // : ReleaseStep, checks whether `publishTo` is properly set up
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
	libraryDependencies := depends
  )

}