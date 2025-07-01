package utp.music.usuario.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import utp.music.usuario.domain.port.in.GetUsuarioUseCase;
import utp.music.usuario.domain.port.in.ManageUsuarioUseCase;
import utp.music.usuario.domain.port.out.UsuarioRepositoryPort;
import utp.music.usuario.domain.service.UsuarioService;

@Configuration
public class UsuarioConfig {

    @Bean
    public UsuarioService usuarioService(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new UsuarioService(usuarioRepositoryPort);
    }

    @Bean
    public ManageUsuarioUseCase manageUsuarioUseCase(UsuarioService usuarioService) {
        return usuarioService;
    }

    @Bean
    public GetUsuarioUseCase getUsuarioUseCase(UsuarioService usuarioService) {
        return usuarioService;
    }
}
