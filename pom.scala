import org.sonatype.maven.polyglot.scala.model._
import scala.collection.immutable.Seq

Model(
  "mirante" % "api" % "0.1.0-SNAPSHOT",
  name = "api",
  description = "Backend for Mirante, a data-oriented educational system",
  properties = Map("java.version" -> "21"),
  parent = Parent(
    gav = "org.springframework.boot" % "spring-boot-starter-parent" % "3.2.0",
    relativePath = ""
  ),
  dependencies = Seq(
    "org.springframework.boot" % "spring-boot-starter-data-jpa" % "",
    "org.springframework.boot" % "spring-boot-starter-data-rest" % "",
    "com.h2database" % "h2" % "" % "runtime",
    "org.springframework.boot" % "spring-boot-starter-test" % "" % "test"
  ),
  build = Build(
    plugins = Seq(
      Plugin(
        "org.springframework.boot" % "spring-boot-maven-plugin"
      )
    )
  ),
  modelVersion = "4.0.0"
)
