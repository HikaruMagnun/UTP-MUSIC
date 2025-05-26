package utp.music.playlist.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.cancion.model.Cancion;
import utp.music.playlist.dto.CreatePlaylistDto;
import utp.music.playlist.dto.UpdatePlaylistDto;
import utp.music.playlist.model.enitity.Playlist;
import utp.music.playlist.service.PlaylistService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public Flux<Playlist> getAllPlaylists() {
        return playlistService.findAll();
    }

    @PostMapping("/add-cancion")
    public Mono<String> addCancionToPlaylist(@RequestParam Long playlistId, @RequestParam Long cancionId) {
        return playlistService.addCancionToPlaylist(playlistId, cancionId)
                .thenReturn("Canción agregada a la playlist exitosamente.");
    }

    @DeleteMapping("/remove-cancion")
    public Mono<String> removeCancionFromPlaylist(@RequestParam Long playlistId, @RequestParam Long cancionId) {
        return playlistService.removeCancionFromPlaylist(playlistId, cancionId)
                .thenReturn("Canción eliminada de la playlist exitosamente.");
    }

    @GetMapping("/list-canciones/{id}")
    public Flux<Cancion> listCancionesFromPlaylist(@PathVariable Long id) {
        return playlistService.listCancionesFromPlaylist(id);
    }

    @PostMapping("/create")
    public Mono<Playlist> createPlaylist(@RequestBody CreatePlaylistDto createPlaylistDto) {
        return playlistService.save(createPlaylistDto);
    }

    @PutMapping("/update")
    public Mono<Playlist> updatePlaylist(@RequestBody UpdatePlaylistDto updatePlaylistDto) {
        return playlistService.updatePlaylist(updatePlaylistDto);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deletePlaylist(@PathVariable Long id) {
        return playlistService.deleteById(id);
    }

    @GetMapping("/list-by-user/{userId}")
    public Flux<Playlist> listPlaylistsByUserId(@PathVariable Long userId) {
        return playlistService.findByUsuarioId(userId);
    }
}
