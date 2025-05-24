package utp.music.playlist.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.playlist.model.enitity.Playlist;
import utp.music.playlist.repository.PlaylistRepository;

@RequiredArgsConstructor
@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public Flux<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    public Mono<Playlist> findById(Long id) {
        return playlistRepository.findById(id);
    }

    public Flux<Playlist> findByUsuarioId(Long usuarioId) {
        return playlistRepository.findByUsuarioId(usuarioId);
    }

    public Mono<Playlist> save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Mono<Void> deleteById(Long id) {
        return playlistRepository.deleteById(id);
    }
}
