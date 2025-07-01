package utp.music.usuario.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.LoginUsuarioDto;
import utp.music.usuario.domain.model.RegisterUserDto;
import utp.music.usuario.domain.model.Usuario;
import utp.music.usuario.domain.port.in.ManageUsuarioUseCase;

@Service
@RequiredArgsConstructor
public class UsuarioApplicationService {

    private final ManageUsuarioUseCase manageUsuarioUseCase;

    public Mono<Usuario> login(LoginUsuarioDto loginDto) {
        return manageUsuarioUseCase.login(loginDto);
    }

    public Mono<Usuario> register(RegisterUserDto registerDto) {
        return manageUsuarioUseCase.register(registerDto);
    }
}
