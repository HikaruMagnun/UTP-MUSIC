package utp.music.playlist.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import utp.music.playlist.domain.port.in.GetPlaylistUseCase;
import utp.music.playlist.domain.port.in.ManagePlaylistUseCase;
import utp.music.playlist.domain.port.out.PlaylistRepositoryPort;
import utp.music.playlist.domain.service.PlaylistService;

@Configuration
public class PlaylistConfig {

    @Bean
    public PlaylistService playlistService(PlaylistRepositoryPort playlistRepositoryPort) {
        return new PlaylistService(playlistRepositoryPort);
    }

    @Bean
    public ManagePlaylistUseCase managePlaylistUseCase(PlaylistService playlistService) {
        return playlistService;
    }

    @Bean
    public GetPlaylistUseCase getPlaylistUseCase(PlaylistService playlistService) {
        return playlistService;
    }
}
