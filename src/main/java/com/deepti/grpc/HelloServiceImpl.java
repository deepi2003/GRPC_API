
package com.deepti.grpc;

import io.grpc.stub.StreamObserver;
    public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(
            HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String greeting = new StringBuilder()
                .append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();

        HelloResponse response = HelloResponse.newBuilder().setGreeting("Deepti")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    }

