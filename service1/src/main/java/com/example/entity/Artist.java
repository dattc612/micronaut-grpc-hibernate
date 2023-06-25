package com.example.entity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Serdeable
@Entity
@Table(name = "artist_tbl")
public class Artist {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "artist_id")
  private int id;

  private String name;

  private int age;

  private int gender;

  public Artist(String name, int age, int gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  @OneToMany(mappedBy ="artist")
  private Set<Album> albums = new HashSet<>();
}
