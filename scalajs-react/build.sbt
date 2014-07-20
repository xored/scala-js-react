import SonatypeKeys._

sonatypeSettings

pomExtra := {
  <scm>
    <connection>scm:git:github.com/xored/scala-js-react.git</connection>
    <developerConnection>scm:git:git@github.com:scala-js-react.git</developerConnection>
    <url>https://github.com/xored/scala-js-react</url>
    <tag>{s"release-$version"}</tag>
  </scm>
  <developers>
    <developer>
      <id>kanterov</id>
      <name>Gleb Kanterov</name>
      <email>gleb@kanterov.ru</email>
    </developer>
  </developers>
}

