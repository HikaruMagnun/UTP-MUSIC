package utp.music.cancion.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.cancion.model.Cancion;
import utp.music.cancion.repository.CancionRepository;

@RequiredArgsConstructor
@Service
public class CancionService {

    private final CancionRepository cancionRepository;

    public Mono<Cancion> findById(Long id) {
        return cancionRepository.findById(id);
    }

    public Flux<Cancion> findByArtistaId(Long artistaId) {
        return cancionRepository.findByArtistaId(artistaId);
    }

    public Flux<Cancion> findByAlbumId(Long albumId) {
        return cancionRepository.findByAlbumId(albumId);
    }

    public Mono<Cancion> findByTitulo(String titulo) {
        return cancionRepository.findByTitulo(titulo);
    }

    public Flux<Cancion> findTop5ByTituloContainingIgnoreCase(String titulo) {
        return cancionRepository.findTop5ByTituloContainingIgnoreCase(titulo);
    }

    public Mono<Cancion> save(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    public Mono<Void> deleteById(Long id) {
        return cancionRepository.deleteById(id);
    }

}
