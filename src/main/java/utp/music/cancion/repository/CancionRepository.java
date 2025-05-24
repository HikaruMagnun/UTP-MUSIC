package utp.music.cancion.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.cancion.model.Cancion;

@Repository
public interface CancionRepository extends R2dbcRepository<Cancion, Long> {
    Flux<Cancion> findByArtistaId(Long artistaId);

    Flux<Cancion> findByAlbumId(Long albumId);

    Mono<Cancion> findByTitulo(String titulo);

    Flux<Cancion> findTop5ByTituloContainingIgnoreCase(String titulo);
}
