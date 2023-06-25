package com.example;

import com.dongen.codegen.ArtistServiceGrpc;
import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;
import jakarta.inject.Singleton;

//@Factory
//@Setter
//@Getter
@Factory
public class Client {


  //  private final PersonServiceGrpc.PersonServiceBlockingStub blockingStub;
  //
  //  public PersonClient(@GrpcChannel("service1") Channel channel) {
  //    this.blockingStub = PersonServiceGrpc.newBlockingStub(channel);
  //  }
  //  public PersionResponse getPerson(String id){
  //    PersonRequest personRequest = PersonRequest.newBuilder().setId("123").build();
  //    return blockingStub.getPerson(personRequest);
  //  }

  @Singleton
  public ArtistServiceGrpc.ArtistServiceBlockingStub blockingStub(
          @GrpcChannel("service1")
          ManagedChannel channel){
    return ArtistServiceGrpc.newBlockingStub(channel);
  }

  @Singleton
  ArtistServiceGrpc.ArtistServiceStub reactiveStub(
          @GrpcChannel("service1")
          ManagedChannel channel){
    return ArtistServiceGrpc.newStub(channel);
  }
}
