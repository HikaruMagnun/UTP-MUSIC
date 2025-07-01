package utp.music.usuario.domain.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.LoginUsuarioDto;
import utp.music.usuario.domain.model.RegisterUserDto;
import utp.music.usuario.domain.model.Rol;
import utp.music.usuario.domain.model.Usuario;
import utp.music.usuario.domain.port.in.GetUsuarioUseCase;
import utp.music.usuario.domain.port.in.ManageUsuarioUseCase;
import utp.music.usuario.domain.port.out.UsuarioRepositoryPort;

@Service
@RequiredArgsConstructor
public class UsuarioService implements ManageUsuarioUseCase, GetUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public Mono<Usuario> login(LoginUsuarioDto loginDto) {
        return usuarioRepositoryPort.findByNombreOrEmail(loginDto.getUsername(), loginDto.getUsername())
                .switchIfEmpty(Mono.error(new IllegalStateException("Usuario no encontrado")))
                .filter(usuario -> usuario.getPassword().equals(loginDto.getPassword()))
                .switchIfEmpty(Mono.error(new IllegalStateException("Contraseña incorrecta")));
    }

    @Override
    public Mono<Usuario> register(RegisterUserDto registerDto) {
        return usuarioRepositoryPort.findByNombreOrEmail(registerDto.getEmail(), registerDto.getEmail())
                .flatMap(existingUser -> Mono.error(
                        new IllegalStateException("El usuario ya existe con el email: " + registerDto.getEmail())))
                .switchIfEmpty(Mono.defer(() -> {
                    Usuario nuevoUsuario = Usuario.create(
                            registerDto.getNombre(),
                            registerDto.getEmail(),
                            registerDto.getPassword(),
                            Rol.ROL_USUARIO);
                    return usuarioRepositoryPort.save(nuevoUsuario);
                })).cast(Usuario.class);
    }

    @Override
    public Mono<Boolean> validateUsuarioWithRole(String email, String password, String role) {
        return usuarioRepositoryPort.findByEmail(email)
                .filter(usuario -> usuario.getPassword().equals(password)
                        && usuario.getRol() == Rol.valueOf(role.toUpperCase()))
                .hasElement();
    }

    @Override
    public Mono<Void> createUsuarioWithRole(String email, String password, String role) {
        Rol rol = Rol.valueOf(role.toUpperCase());
        Usuario usuario = Usuario.create("", email, password, rol);
        return usuarioRepositoryPort.save(usuario).then();
    }

    @Override
    public Flux<Usuario> listarTodos() {
        return usuarioRepositoryPort.findAll();
    }

    @Override
    public Mono<Usuario> buscarPorId(Long id) {
        return usuarioRepositoryPort.findById(id);
    }

    @Override
    public Mono<Usuario> guardar(Usuario usuario) {
        return usuarioRepositoryPort.save(usuario);
    }

    @Override
    public Mono<Void> eliminar(Long id) {
        return usuarioRepositoryPort.deleteById(id);
    }
}
