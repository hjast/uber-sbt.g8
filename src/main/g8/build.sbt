
import sbtrelease._
import ReleaseStateTransformations._

import $name$Build._

import Dependencies._

name := "$name$"

organization := "$org$"

version := "$version$"

scalaVersion := "$scala_version$"

scalacOptions ++= Seq(
                      "-feature",
                      "-language:higherKinds",
                      "-language:implicitConversions",
                      "-deprecation",
                      "-unchecked"
                    )
