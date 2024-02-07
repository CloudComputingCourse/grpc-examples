package cmu.cc15619;

import cmu.cc15619.rpc.echo.EchoRequest;
import cmu.cc15619.rpc.echo.EchoResponse;
import cmu.cc15619.rpc.echo.EchoServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Scanner;

public class RPCClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("127.0.0.1:8080")
                .usePlaintext()
                .build();

        EchoServiceGrpc.EchoServiceBlockingStub stub = EchoServiceGrpc.newBlockingStub(channel);
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        while (true) {
            System.out.println("Enter a message: ");
            String message = scanner.nextLine();
            try {
                EchoResponse response = stub.echo(EchoRequest.newBuilder().setMessage(message).setNumber(number).build());
                System.out.println("Received message: " + response.getMessage());
            } catch (Exception e) {
                System.out.println("Exception received. Is the server running correctly?");
                e.printStackTrace();
                return;
            }
        }
    }
}
