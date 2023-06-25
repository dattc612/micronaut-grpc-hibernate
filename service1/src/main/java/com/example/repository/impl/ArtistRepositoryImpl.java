package com.example.repository.impl;

import com.example.entity.Artist;
import com.example.repository.ArtistRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Singleton
public class ArtistRepositoryImpl implements ArtistRepository {
  private final EntityManager entityManager;

  public ArtistRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @ReadOnly
  public Optional<Artist> findById(int id){
    return Optional.ofNullable(entityManager.find(Artist.class, id));
  }

  @Override
  @Transactional
  public Artist save(String name, int age, int gender) {
    Artist artist = new Artist(name, age, gender);
    entityManager.persist(artist);
    return artist;
  }

  @Override
  @Transactional
  public void deleteById(int id) {
    entityManager.remove(findById(id));
  }

  @Override
  @ReadOnly
  public List<Artist> findAll() {
    String qlString = "FROM Artist";
    TypedQuery<Artist> query = entityManager.createQuery(qlString, Artist.class);
    return query.getResultList();
  }

}
