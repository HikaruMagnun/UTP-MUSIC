package utp.music.artista.domain.port.in;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.artista.domain.model.Artista;

public interface GetArtistaUseCase {
    Flux<Artista> findAll();

    Mono<Artista> findById(Long id);

    Flux<Artista> searchArtistas(String nombreArtistico);

    Mono<Artista> findByEmail(String email);
}
