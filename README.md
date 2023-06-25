# Demo
# Setup environment

Using docker create postgresql container 

```agsl
 docker run --name demo-postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```
Exec into postgres container with:
```bash
docker exec -it demo-postgres psql -U  postgres
```
Run this script to create database and insert data.
```roomsqll
CREATE DATABASE demo;
\c demo; 


CREATE TABLE artist_tbl (
    artist_id integer NOT NULL,
    name character varying,
    age integer,
    gender integer,
    PRIMARY KEY (artist_id)
);
CREATE TABLE album_tbl (
    album_id integer NOT NULL,
    album_name character varying,
    artist_id integer NOT NULL,
    genre character varying,
    PRIMARY KEY (album_id),
    CONSTRAINT fk_album
          FOREIGN KEY(artist_id)
          REFERENCES artist_tbl(artist_id)
);



INSERT INTO artist_tbl (artist_id, age, gender, name) VALUES (1, 23, 0, 'The Weeknd');
INSERT INTO artist_tbl (artist_id, age, gender, name) VALUES (2, 29, 0, 'Twin Atlantic');
INSERT INTO artist_tbl (artist_id, age, gender, name) VALUES (3, 26, 0, 'RuPaul');
INSERT INTO artist_tbl (artist_id, age, gender, name) VALUES (4, 23, 0, 'Cao Thanh Dat');

INSERT INTO album_tbl (album_id, album_name, artist_id, genre) VALUES (1, 'The Dreamers', 4, '	Jazz');
INSERT INTO album_tbl (album_id, album_name, artist_id, genre) VALUES (2, 'Dawn FM', 3, '	Jazz');
INSERT INTO album_tbl (album_id, album_name, artist_id, genre) VALUES (3, 'Transparency', 1, 'Pop');
INSERT INTO album_tbl (album_id, album_name, artist_id, genre) VALUES (4, 'Family', 4, 'Pop');

```

Change address in application.yml (/micronaut-grpc-hibernate/service1/src/main/resources/application.yml)if not running container in local

```

datasources:
  default:
    url: ${JDBC_URL:`jdbc:postgresql://localhost:5432/demo`}
    username: ${JDBC_USER:postgres}
    password: ${JDBC_PASSWORD:postgres}
    driverClassName: ${JDBC_DRIVER:org.postgresql.Driver}
```