package cmu.cc15619;

import cmu.cc15619.rpc.echo.EchoRequest;
import cmu.cc15619.rpc.echo.EchoResponse;
import cmu.cc15619.rpc.echo.EchoServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Scanner;

public class RPCClient {
    public static void main(String[] args) {
        // Connect to the gRPC server
        ManagedChannel channel = ManagedChannelBuilder.forTarget("127.0.0.1:8080")
                .usePlaintext()
                .build();
        // Create a blocking stub. It contains methods defined in EchoService.proto
        EchoServiceGrpc.EchoServiceBlockingStub stub = EchoServiceGrpc.newBlockingStub(channel);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a message: ");
            String message = scanner.nextLine();
            try {
                EchoResponse response = stub.echo(EchoRequest.newBuilder().setMessage(message).build());
                System.out.println("Received message: " + response.getMessage());
            } catch (Exception e) {
                System.out.println("Exception received. Is the server running correctly?");
                e.printStackTrace();
                return;
            }
        }
    }
}
