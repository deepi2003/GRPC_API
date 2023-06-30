package com.deepti.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GRPCServer {
    private static final Logger logger = Logger.getLogger(GRPCServer.class.getName());
    private Server server;
    private void start() throws IOException {
        int port = 1234;
        server = ServerBuilder.forPort(port)
                .addService(new HelloServiceImpl())
                .addService(ProtoReflectionService.newInstance())
                .build().start();

        logger.info("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shutting down gRPC server");
                try {
                    server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        final GRPCServer greetServer = new GRPCServer();
        greetServer.start();
        greetServer.server.awaitTermination();
    }
}
