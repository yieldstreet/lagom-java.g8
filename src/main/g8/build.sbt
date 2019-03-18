organization in ThisBuild := "$organization$"
version in ThisBuild := "$version$"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.8"

lagomCassandraEnabled in ThisBuild := false

lagomKafkaEnabled in ThisBuild := false

lazy val `$name;format="norm"$` = (project in file("."))
  .aggregate(`$name;format="norm"$-model`, `$name;format="norm"$-api`, `$name;format="norm"$-impl`)
  .settings(publishArtifact := false)

lazy val `$name;format="norm"$-model` = (project in file("$name;format="norm"$-model"))
  .enablePlugins(YieldstreetModel)
  .settings(
    autoScalaLibrary := false,
    libraryDependencies ++= Seq(
      yieldstreetCoreModel,
      validationApi,
      lombok
    )
  )

lazy val `$name;format="norm"$-api` = (project in file("$name;format="norm"$-api"))
  .enablePlugins(YieldstreetApi)
  .settings(
    libraryDependencies ++= Seq(
      yieldstreetCoreApi
    )
  )
  .dependsOn(`$name;format="norm"$-model`)

lazy val `$name;format="norm"$-impl` = (project in file("$name;format="norm"$-impl"))
  .enablePlugins(YieldstreetLagomJava)
  .settings(
    publishArtifact := false,
    libraryDependencies ++= Seq(
      lagomJavadslTestKit,
      logbackRedisAppender,
      akkaClusterBootstrap,
      akkaClusterHttpManagement,
      akkaDiscoveryKubernetes,
      hibernateValidator,
      yieldstreetCoreImpl
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`$name;format="norm"$-api`)
