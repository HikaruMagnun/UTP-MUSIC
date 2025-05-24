package utp.music.artista.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import utp.music.artista.model.Artista;

@Repository
public interface ArtistaRepository extends R2dbcRepository<Artista, Long> {
}
