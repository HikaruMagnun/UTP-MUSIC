package utp.music.usuario.domain.port.in;

import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.LoginUsuarioDto;
import utp.music.usuario.domain.model.RegisterUserDto;
import utp.music.usuario.domain.model.Usuario;

public interface ManageUsuarioUseCase {
    Mono<Usuario> login(LoginUsuarioDto loginDto);

    Mono<Usuario> register(RegisterUserDto registerDto);

    Mono<Boolean> validateUsuarioWithRole(String email, String password, String role);

    Mono<Void> createUsuarioWithRole(String email, String password, String role);
}
