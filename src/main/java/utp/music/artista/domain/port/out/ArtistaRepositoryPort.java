package utp.music.artista.domain.port.out;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.artista.domain.model.Artista;

public interface ArtistaRepositoryPort {
    Flux<Artista> findAll();

    Mono<Artista> findById(Long id);

    Mono<Artista> save(Artista artista);

    Mono<Void> deleteById(Long id);

    Mono<Artista> findByNombreArtistico(String nombreArtistico);

    Flux<Artista> findTop5ByNombreArtisticoContainingIgnoreCase(String nombreArtistico);
}
