package cmu.cc15619;

import io.grpc.stub.StreamObserver;
import cmu.cc15619.rpc.proto.EchoRequest;
import cmu.cc15619.rpc.proto.EchoResponse;
import cmu.cc15619.rpc.proto.EchoServiceGrpc;

import java.util.logging.Logger;

public class EchoServerImpl extends EchoServiceGrpc.EchoServiceImplBase {
    private static final Logger logger = Logger.getLogger(EchoServerImpl.class.getName());
    public EchoServerImpl() {
    }

    public void echo(EchoRequest request, StreamObserver<EchoResponse> responseObserver) {
        String message = request.getMessage();
        EchoResponse response = EchoResponse.newBuilder()
                .setNumber(request.getNumber())
                .setMessage(message)
                .build();
        logger.log(java.util.logging.Level.INFO, "Received message: " + message);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
