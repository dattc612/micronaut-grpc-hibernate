package com.example.controller;

import com.dongen.codegen.ArtistRequest;
import com.dongen.codegen.ArtistResponse;
import com.dongen.codegen.ArtistServiceGrpc;
import com.dongen.codegen.Empty;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

@Controller("/artist")
public class PersonController {
  ArtistServiceGrpc.ArtistServiceBlockingStub  stub;
  ArtistServiceGrpc.ArtistServiceStub reactiveStub;
  private static Logger logger = LoggerFactory.getLogger(PersonController.class);


  public PersonController(ArtistServiceGrpc.ArtistServiceBlockingStub stub, ArtistServiceGrpc.ArtistServiceStub reactiveStub) {
    this.stub = stub;
    this.reactiveStub = reactiveStub;
  }

  @Get(value = "{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public String getPerson(@QueryValue("id") int id) {
    ArtistRequest artistRequest= ArtistRequest.newBuilder().setId(id).build();
    ArtistResponse artistResponse = stub.getPerson(artistRequest);
    return artistResponse.getId()  == 0 ? "Nothing" : artistResponse.toString();
  }

  @Get(value = "/list")
//  @Produces(MediaType.TEXT_PLAIN)
  public void getListPerson() {
     Empty emptyRequest = Empty.newBuilder().build();
    Iterator<ArtistResponse> artists = stub.getAllPerson(emptyRequest);
    while(artists.hasNext()){
      logger.info(artists.next().toString());
    }
  }
}
