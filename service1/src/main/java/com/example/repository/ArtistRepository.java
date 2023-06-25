package com.example.repository;

import com.example.entity.Artist;
import io.micronaut.data.annotation.Repository;



import java.util.List;
import java.util.Optional;

//@Repository
public interface ArtistRepository {
  Optional<Artist> findById(int id);
  Artist save(String name, int age, int gender);
  void deleteById(int id);
  List<Artist> findAll();

}
