package utp.music.playlist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
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

}
