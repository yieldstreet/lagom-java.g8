organization in ThisBuild := "$organization$"
version in ThisBuild := "$version$"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

lagomCassandraEnabled in ThisBuild := false

lagomKafkaEnabled in ThisBuild := false

lazy val `$name;format="norm"$` = (project in file("."))
  .aggregate(`$name;format="norm"$-api`, `$name;format="norm"$-impl`)

lazy val `$name;format="norm"$-api` = (project in file("$name;format="norm"$-api"))
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      lombok
    )
  )

lazy val `$name;format="norm"$-impl` = (project in file("$name;format="norm"$-impl"))
  .enablePlugins(LagomJava)
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslTestKit,
      lombok
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`$name;format="norm"$-api`)


val lombok = "org.projectlombok" % "lombok" % "1.16.10"

def common = Seq(
  javacOptions in compile += "-parameters"
)
