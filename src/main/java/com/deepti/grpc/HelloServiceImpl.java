
package com.deepti.grpc;

import com.google.common.io.Resources;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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

        HelloResponse response = HelloResponse.newBuilder().setGreeting(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void grpc100KB(Empty par,
                                 StreamObserver<BigResponse> responseObserver) {
        BigResponse response = BigResponse.newBuilder().setResponse(getFileContents("ResponseFile/Response.txt"))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void grpc500KB(Empty par,
                          StreamObserver<BigResponse> responseObserver) {
        BigResponse response = BigResponse.newBuilder().setResponse(getFileContents("ResponseFile/Response_1.txt"))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void grpc1MB(Empty par,
                          StreamObserver<BigResponse> responseObserver) {
        BigResponse response = BigResponse.newBuilder().setResponse(getFileContents("ResponseFile/Response_2.txt"))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void grpc5MB(Empty par,
                          StreamObserver<BigResponse> responseObserver) {
        BigResponse response = BigResponse.newBuilder().setResponse(getFileContents("ResponseFile/Response_3.txt"))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private static String getFileContents(String resourceName) {
        URL url = Resources.getResource(resourceName);
        String res;
        try {
            res = Resources.toString(url, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    }

