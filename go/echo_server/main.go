package main

import (
	pb "Echo/protobuf"
	"context"
	"net"
	"os"
	"os/signal"
	"syscall"
	"time"

	"google.golang.org/grpc"
)

type EchoServer struct {
	pb.UnimplementedEchoServiceServer
}

func (s *EchoServer) Echo(ctx context.Context, req *pb.EchoRequest) (resp *pb.EchoResponse, err error) {
	resp = &pb.EchoResponse {
		Message: req.Message,
		Number:  req.Number,
	}
	return
}

func main() {
	//Start grpc server
	go func() {
		lis, err := net.Listen("tcp", ":9000")
		if err != nil {
			panic(err)
		}
		var opts []grpc.ServerOption
		grpcServer := grpc.NewServer(opts...)
		println("Start gRPC server at 127.0.0.1:9000")
		pb.RegisterEchoServiceServer(grpcServer, &EchoServer{})
		grpcServer.Serve(lis)
	}()

	// Graceful shutdown
	quit := make(chan os.Signal)
	signal.Notify(quit, syscall.SIGINT, syscall.SIGTERM)
	<-quit
	// Exit gracefully in 1 seconds
	_, cancel := context.WithTimeout(context.Background(), time.Second*1)
	defer cancel()
}
