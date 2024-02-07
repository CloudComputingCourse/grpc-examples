# gRPC Java Project setup
## Overview
This is a template project for the gRPC and Protocol Buffers hands-on task in gRPC primer. It uses standard gRPC Java libraries and
`com.google.protobuf` gradle plugin for Protocol Buffers code generation. See also: [gRPC Java Quick Start](https://grpc.io/docs/languages/java/quickstart/)
## Prerequisites
- Java 17 (corretto-17)

## Getting Started
Run server:
```shell
./gradlew runServer
```
Run client:
```shell
./gradlew runClient
```
Run protobuf code generation alone:
```shell
./gradlew generateProto