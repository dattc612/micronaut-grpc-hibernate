package com.example.endpoint;

import com.dongen.codegen.ArtistResponse;
import com.dongen.codegen.ArtistRequest;
import com.dongen.codegen.ArtistServiceGrpc;
import com.dongen.codegen.Empty;
import com.example.entity.Artist;
import com.example.repository.ArtistRepository;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ArtistServer extends ArtistServiceGrpc.ArtistServiceImplBase {

    private final ArtistRepository artistRepository;

    public ArtistServer(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void getPerson(ArtistRequest personRequest, StreamObserver<ArtistResponse> responseObserver){

        // handler
        Optional<Artist> artist = artistRepository.findById(personRequest.getId());


        ArtistResponse response = ArtistResponse.newBuilder().build();

        if (!artist.isEmpty()){
            response = ArtistResponse.newBuilder()
                    .setAge(artist.get().getAge())
                    .setName(artist.get().getName())
                    .setId(artist.get().getId())
                    .setGender(artist.get().getGender()).build();
        }



        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void getAllPerson(Empty request, StreamObserver<ArtistResponse> responseObserver) {
        List<Artist> artists =  artistRepository.findAll();
        for (Artist artist : artists) {
            ArtistResponse responseArtist = ArtistResponse.newBuilder()
                    .setAge(artist.getAge())
                    .setName(artist.getName())
                    .setId(artist.getId())
                    .setGender(artist.getGender()).build();
            responseObserver.onNext(responseArtist);
        }
        responseObserver.onCompleted();
    }
}
