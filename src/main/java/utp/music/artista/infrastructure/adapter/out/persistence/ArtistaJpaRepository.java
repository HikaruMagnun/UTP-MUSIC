package utp.music.artista.infrastructure.adapter.out.persistence;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ArtistaJpaRepository extends R2dbcRepository<ArtistaEntity, Long> {
    Mono<ArtistaEntity> findByNombreArtistico(String nombreArtistico);

    Flux<ArtistaEntity> findTop5ByNombreArtisticoContainingIgnoreCase(String nombreArtistico);
}
