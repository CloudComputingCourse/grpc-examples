package cmu.cc15619;

import io.grpc.stub.StreamObserver;
import cmu.cc15619.rpc.echo.EchoRequest;
import cmu.cc15619.rpc.echo.EchoResponse;
import cmu.cc15619.rpc.echo.EchoServiceGrpc;

import java.util.logging.Logger;

public class EchoServerImpl extends EchoServiceGrpc.EchoServiceImplBase {
    private static final Logger logger = Logger.getLogger(EchoServerImpl.class.getName());
    public EchoServerImpl() {
    }

    public void echo(EchoRequest request, StreamObserver<EchoResponse> responseObserver) {
        String message = request.getMessage();
        // Construct a response
        EchoResponse response = EchoResponse.newBuilder()
                .setMessage(message)
                .build();
        logger.log(java.util.logging.Level.INFO, "Received message: " + message);
        // Use RxJava style observer pattern to send the response asynchronously
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
