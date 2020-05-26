# Controller for Simple Receive Dispatch

## Introduction
This repository is the Controller module in an MVC architecture; a RESTful Scalatra Route/Action pairs, prepared to work with two other repositories, naming:
- Model module: https://github.com/shvahabi/simple-receive-dispatch-model
- View module: https://github.com/shvahabi/simple-receive-dispatch-view

## Dependencies
[Sbt](https://www.scala-sbt.org/), the interactive build tool, is required to build and run this repository.

## Instructions
By following this step by step instructions, you will set up a REST server listening on `localhost:8080` which mediates View and Model from above repositories:
- $ `git clone https://github.com/shvahabi/simple-receive-dispatch-controller.git`
- $ `cd simple-receive-dispatch-controller/`
- before continue any further you have to hard-code database path, username and password within `before()` Route of `simple-receive-dispatch-controller/src/main/scala/com/bsp/receivedispatch/controller/ReceiveDispatchServlet.scala` as created per **Instructions** of Model module mentioned above.
- $ `sbt`
- > jetty:start

## Release Notes
Version 0.40.0 the stable successor, with only several bugfixes, to what showcased through Skype on April 21st 2020 to Inception team members.
