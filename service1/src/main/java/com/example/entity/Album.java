package com.example.entity;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Serdeable
@Entity
@Table(name = "album_tbl")
public class Album {
  @Id
  @Column(name = "album_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @Column(name = "album_name")
  private String albumName;

  @Column(name = "artist_id")
  private int artistId;

  private String genre;

  @ManyToOne
  private Artist artist;
}
