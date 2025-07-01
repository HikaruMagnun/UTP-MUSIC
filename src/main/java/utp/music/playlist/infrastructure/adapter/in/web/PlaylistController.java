package utp.music.playlist.infrastructure.adapter.in.web;

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
import utp.music.playlist.application.service.PlaylistApplicationService;
import utp.music.playlist.domain.model.ApiResponse;
import utp.music.playlist.domain.model.CreatePlaylistDto;
import utp.music.playlist.domain.model.Playlist;
import utp.music.playlist.domain.model.UpdatePlaylistDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistApplicationService playlistApplicationService;

    @GetMapping
    public Flux<Playlist> getAllPlaylists() {
        return playlistApplicationService.getAllPlaylists();
    }

    @PostMapping("/add-cancion")
    public Mono<ApiResponse> addCancionToPlaylist(@RequestParam Long playlistId, @RequestParam Long cancionId) {
        return playlistApplicationService.addCancionToPlaylist(playlistId, cancionId);
    }

    @DeleteMapping("/remove-cancion")
    public Mono<ApiResponse> removeCancionFromPlaylist(@RequestParam Long playlistId, @RequestParam Long cancionId) {
        return playlistApplicationService.removeCancionFromPlaylist(playlistId, cancionId);
    }

    @GetMapping("/list-canciones/{id}")
    public Flux<Object> listCancionesFromPlaylist(@PathVariable Long id) {
        return playlistApplicationService.listCancionesFromPlaylist(id);
    }

    @PostMapping("/create")
    public Mono<Playlist> createPlaylist(@RequestBody CreatePlaylistDto createPlaylistDto) {
        return playlistApplicationService.createPlaylist(createPlaylistDto);
    }

    @PutMapping("/update")
    public Mono<Playlist> updatePlaylist(@RequestBody UpdatePlaylistDto updatePlaylistDto) {
        return playlistApplicationService.updatePlaylist(updatePlaylistDto);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deletePlaylist(@PathVariable Long id) {
        return playlistApplicationService.deletePlaylist(id);
    }

    @GetMapping("/list-by-user/{userId}")
    public Flux<Playlist> listPlaylistsByUserId(@PathVariable Long userId) {
        return playlistApplicationService.listPlaylistsByUserId(userId);
    }
}
