package main

import (
	pb "EchoClient/protobuf"
	"context"
	"log"

	"google.golang.org/grpc"
)

func main() {
	// create a TCP connection
	conn, err := grpc.Dial("127.0.0.1:9000", grpc.WithInsecure())
	if err != nil {
		log.Fatalf("grpc.Dial err: %v", err)
	}
	defer conn.Close()

	// create a stub with connection
	client := pb.NewEchoServiceClient(conn)

	// call the remote function like a local function
	resp, err := client.Echo(context.Background(), &pb.EchoRequest{
		Message: "Good Luck in Team Project!",
		Number:  15619,
	})
	if err != nil {
		log.Fatalf("client.Search err: %v", err)
	}

	println("Response Message: ", resp.Message)
	println("Response Number: ", resp.Number)
}
