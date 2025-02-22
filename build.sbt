lazy val sbtJavaFormatter = project.in(file(".")).aggregate(plugin).settings(skip in publish := true)

lazy val plugin = project
  .in(file("plugin"))
  .enablePlugins(SbtPlugin)
  .settings(
    organization := "io.github.hubertp",
    name := "sbt-java-formatter",
    homepage := scmInfo.value.map(_.browseUrl),
    scmInfo := Some(ScmInfo(url("https://github.com/sbt/sbt-java-formatter"), "scm:git:git@github.com:sbt/sbt-java-formatter.git")),
    developers := List(Developer("ktoso", "Konrad 'ktoso' Malawski", "<ktoso@project13.pl>", url("https://github.com/ktoso"))),
    libraryDependencies ++= Seq(
        "com.google.googlejavaformat" % "google-java-format" % "1.18.1"
    ),

    startYear := Some(2015),
    description := "Formats Java code in your project.",
    licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
    publishTo := Some(if (isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging),
    crossSbtVersions := List("1.3.0"),
    scalacOptions ++= Seq("-encoding", "UTF-8", "-unchecked", "-deprecation", "-feature"),
    javacOptions ++= Seq("-encoding", "UTF-8"),
    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++
      Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
    },
    scriptedBufferLog := false,
    scalafmtOnCompile := true,
  )
  .enablePlugins(AutomateHeaderPlugin)
