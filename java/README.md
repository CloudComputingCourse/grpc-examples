# gRPC Java Project setup
## Overview
This is a template project for the gRPC and Protocol Buffers hands-on task in gRPC primer. It uses standard gRPC Java libraries and
`com.google.protobuf` gradle plugin for Protocol Buffers code generation. See also: [gRPC Java Quick Start](https://grpc.io/docs/languages/java/quickstart/)
## Prerequisites
- Java 17 (corretto-17)

## Getting Started
Install Java 17 (corretto-17) and set JAVA_HOME environment variable to point to the installation directory.
Ubuntu 22.04:
```shell
sudo apt update
sudo apt install openjdk-17-jdk
```
Check the installation:
```shell
java -version
```
Run server:
```shell
./gradlew runServer
```
You should be able to see the server running on port 8080.

Run client (in another terminal):
```shell
./gradlew runClient
```
Input any string and press enter. You should see the message being sent to the server and the server
responding with the same message. Logs should be printed in both client and server.

If you want to see what the generated code looks like, you can run protobuf code generation alone:
```shell
./gradlew generateProto
```
The generated code will be in `build/generated/source/proto/main` directory.