package utp.music.artista.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import utp.music.artista.domain.port.in.GetArtistaUseCase;
import utp.music.artista.domain.port.in.ManageArtistaUseCase;
import utp.music.artista.domain.port.out.ArtistaRepositoryPort;
import utp.music.artista.domain.port.out.UsuarioRepositoryPort;
import utp.music.artista.domain.service.ArtistaService;

@Configuration
public class ArtistaConfig {

    @Bean
    public ArtistaService artistaService(ArtistaRepositoryPort artistaRepositoryPort,
            UsuarioRepositoryPort usuarioRepositoryPort) {
        return new ArtistaService(artistaRepositoryPort, usuarioRepositoryPort);
    }

    @Bean
    public ManageArtistaUseCase manageArtistaUseCase(ArtistaService artistaService) {
        return artistaService;
    }

    @Bean
    public GetArtistaUseCase getArtistaUseCase(ArtistaService artistaService) {
        return artistaService;
    }
}
