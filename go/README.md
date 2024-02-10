# gRPC Go Project setup

## Overview

This is a template project for the gRPC and Protocol Buffers hands-on task in gRPC primer. It uses standard gRPC Java libraries and
`com.google.protobuf` gradle plugin for Protocol Buffers code generation. See also: [gRPC Java Quick Start](https://grpc.io/docs/languages/java/quickstart/)

## Prerequisites
- GoLang (1.21)

## Getting Started

### Installation

```
###Install Go
sudo apt-get update && sudo snap install go --classic

## Verify installation
go version

# Expect output:
# go version go1.21.6 linux/amd64

## Install protocol buffer compiler
sudo apt install protobuf-compiler

## Verify installation
protoc --version

# Expected output:
# libprotoc 3.12.4

```
### Compiling Protocol Buffers

- Install `protoc` plugins that generate the Go code of the protocol buffer language 

```
go install google.golang.org/protobuf/cmd/protoc-gen-go@v1.28
go install google.golang.org/grpc/cmd/protoc-gen-go-grpc@v1.2
```

- Update your PATH variable to include the bin directory of the Go workspace. 
```
export PATH="$PATH:$(go env GOPATH)/bin"
```

- Run the following command to generate the Go code using `protoc`. 

```
cd echo_server
protoc --go_out=. --go_opt=paths=source_relative --go-grpc_out=. --go-grpc_opt=paths=source_relative  echo.proto
```
- The above command generates the Go code and Go gRPC code  in the current directory by using `--go_out=.` and  `--go-grpc_out=.` To ensure the generated code has the correct import path, we also specified that `--go_opt=paths=source_relative` and `--go-grpc_opt=paths=source_relative`

- Specify the `.proto` files to be compiled. In our case, we only need to compile the `echo.proto` file

- Check if you successfully generated the Go code for your `echo.proto` file:

```
ls

# expected output:
# echo.pb.go  echo.proto  echo_grpc.pb.go
```

### Building and running the application

#### Building the server

```
cd echo_server
go mod init Echo
go mod tidy

go build
```

#### Building the client

```
cd echo_client

## Copy and paste the protobuf folder from echo_server to the echo_client folder.
## The client needs to be aware of the defined fields.

cp -r  ~/echo_server/protobuf ./

go mod init EchoClient
go mod tidy

go build
```

#### Running the app

- In the first terminal, run the echo server:

```
cd ~/echo_server
go run main.go
```

- In the second terminal, run the echo client:
  
```
cd ~/echo_client
go run main.go
```

- When run succesfully, you should see the following output 

```
Response Message:  Good Luck in Team Project!
Response Number:  15619
```

### Troubleshooting

- If you encounter issues such as `protoc-gen-go: program not found or is not executable`, please make sure that you have successfully installed dependencies using above mentioned `go install` commands and exported the path correctly. 
