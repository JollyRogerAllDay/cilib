addSbtPlugin("com.typesafe.sbtscalariform" % "sbtscalariform" % "0.4.0")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.4")

resolvers += Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
