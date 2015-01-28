/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.github

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
