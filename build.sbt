import com.typesafe.sbt.packager.docker.DockerPermissionStrategy

enablePlugins(GitVersioning, LauncherJarPlugin, DockerPlugin)

name := "hello-uzhttp"

scalaVersion := "2.13.1"

val zioVersion = "1.0.0-RC18-2"

libraryDependencies ++= Seq(
  "org.polynote" %% "uzhttp"       % "0.1.2",

  "dev.zio"      %% "zio-test-sbt" % zioVersion % "test",
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

Global / cancelable := false

dockerUpdateLatest := true
dockerBaseImage := "gcr.io/distroless/java:11"
daemonUserUid in Docker := None
daemonUser in Docker := "root"
dockerPermissionStrategy := DockerPermissionStrategy.None
dockerEntrypoint := Seq("java", "-jar",s"/opt/docker/lib/${(artifactPath in packageJavaLauncherJar).value.getName}")
dockerCmd :=  Seq.empty
