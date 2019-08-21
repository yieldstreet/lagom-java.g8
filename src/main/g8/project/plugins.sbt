// Yieldstreet Nexus Repository
resolvers += "maven-public" at "https://nexus.ops.yieldstreet.net/repository/maven-public/"

// The YieldStreet Lagom plugin
addSbtPlugin("yieldstreet" % "yieldstreet-lagom-sbt-plugin" % "$core_sbt_plugin_version$")
