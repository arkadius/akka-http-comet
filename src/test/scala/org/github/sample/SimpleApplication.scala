package org.github.sample

import akka.actor.ActorSystem
import akka.http.Http
import akka.http.client.RequestBuilding
import akka.http.server._
import akka.stream.FlowMaterializer

object SimpleApplication extends App with RequestBuilding  {
  import akka.http.server.Directives._
  import akka.http.server.RouteResult._

  implicit val system = ActorSystem()
  implicit val materializer = FlowMaterializer()

  implicit val routingSetup: RoutingSetup = RoutingSetup(
    routingSettings = RoutingSettings.default,
    executionContext = system.dispatcher,
    flowMaterializer = materializer,
    routingLog = RoutingLog(system.log)
  )

  val serverBinding = Http(system).bind(interface = "localhost", port = 8080)

  serverBinding.startHandlingWith {
    path(Rest) { path =>
      get {
        getFromFile("src/test/resources/samplewebapp/index.html")
      }
    }
  }

}
