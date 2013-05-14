###Über sbt template###

#### G8 & Go ####

**A highly opionated sbt template with tons of nice plugins, an admin console and dependencies ready to go!**

####Motivation####

* Easy way to upgrade libraries, easy access all the libraries (I use, but I expect to include more).
* Since I use less than 20-30 libraries in my projects, actually having dependency listing part of template. This way it is both more readable and dependencies are autocompletable.
* A console (since I go down into the console a lot). Basically any method you add to AdminConsole, you can call directly from console without having to import anything
* Latest version of sbt + sbt plugins which I use
* A way to basically let new programmers know which libraries they **should** use (anything in the Dependencies.scala)
* logback + resource files for most scala projects

To use 

```shell
g8r hjast/uberSBT
cd uberSBT
sbt
gen-idea
//Open up in IntelliJ
````

To change dependencies either change in the Build.scala file. 
```scala
/** You can use any of the predefined dependencies or you can do traditional dep. adding **/
dependencies = Seq(lift.webkit, akka.actor, akka.camel)

```
or in build.sbt
``` scala
libraryDependencies ++= akka.all
```

To use console at sbt prompt

```scala
console-admin
```

Now anything added in AdminConsole can be called directly.

####List of Dependencies Included####

Please send a pull request to add any other dependencies to see missing (high quality libraries which could be used outside there own ecosystem.)

#####Akka######
akka.all [Seq]
akka.actor
akka.agent
akka.camel
akka.dataflow
akka.fileMailbox
akka.kernel
akka.mailboxesCommon
akka.osgi
akka.osgiAries
akka.remote
akka.slf4j
akka.transactor
akka.testkit
akka.zeromq

#####Spray#####

spray.all [Seq]
spray.caching
spray.can
spray.client
spray.http
spray.httpx
spray.io 
spray.routing
spray.servlet
spray.testkit
spray.util

#####Lift#####

lift.all [Seq]
lift.webkit
lift.mapper
lift.record
lift.wizard
lift.json
lift.mongo
lift.mongoRecord

#####Shapeless#####
shapeless.core
shapeless.spire
shapeless.scalaz
shapeless.scalacheck

#####Testing#####
specs2
junit

#####Java#####

commonLang
servlet
jodaTime

#####Numerical#####
saddle
spire

#####NoSql#####
rogueAll [Seq]
rogueField
rogueCore
rogueLift

#####Util#####
scalaLogging
scalaTime
logback
slf4j
logLady
