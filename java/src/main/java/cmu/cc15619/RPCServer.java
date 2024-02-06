package cmu.cc15619;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class RPCServer {
    public static void main(String[] args) {
        // Build a gRPC server
        Server server = ServerBuilder.forPort(8080)
                .addService(new EchoServerImpl())
                .build();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
