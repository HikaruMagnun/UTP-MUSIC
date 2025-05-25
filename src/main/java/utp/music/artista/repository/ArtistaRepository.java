package utp.music.artista.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.artista.model.Artista;

@Repository
public interface ArtistaRepository extends R2dbcRepository<Artista, Long> {
    Mono<Artista> findByNombreArtistico(String nombreArtistico);
    Flux<Artista> findTop5ByNombreArtisticoContainingIgnoreCase(String nombreArtistico);
}
