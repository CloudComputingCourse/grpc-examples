package main

import (
	pb "EchoClient/protobuf"
	"context"
	"log"

	"google.golang.org/grpc"
)

func main() {
	conn, err := grpc.Dial(":9000", grpc.WithInsecure())
	if err != nil {
		log.Fatalf("grpc.Dial err: %v", err)
	}
	defer conn.Close()

	client := pb.NewEchoServiceClient(conn)
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
