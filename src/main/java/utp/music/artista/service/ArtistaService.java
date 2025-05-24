package utp.music.artista.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.artista.model.Artista;
import utp.music.artista.repository.ArtistaRepository;

@RequiredArgsConstructor
@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public Flux<Artista> findAll() {
        return artistaRepository.findAll();
    }

    public Mono<Artista> findById(Long id) {
        return artistaRepository.findById(id);
    }

    public Mono<Artista> save(Artista artista) {
        return artistaRepository.save(artista);
    }

    public Mono<Void> deleteById(Long id) {
        return artistaRepository.deleteById(id);
    }
}
