package utp.music.usuario.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.usuario.model.dto.LoginUsuarioDto;
import utp.music.usuario.model.dto.RegisterUserDto;
import utp.music.usuario.model.entity.Rol;
import utp.music.usuario.model.entity.Usuario;
import utp.music.usuario.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Flux<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Mono<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Mono<Usuario> guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Mono<Void> eliminar(Long id) {
        return usuarioRepository.deleteById(id);
    }

    public Mono<Usuario> login(LoginUsuarioDto loginDto) {
        return usuarioRepository.findByNombreOrEmail(loginDto.getUsername(), loginDto.getUsername())
                .switchIfEmpty(Mono.error(new IllegalStateException("Usuario no encontrado")))
                .filter(usuario -> usuario.getPassword().equals(loginDto.getPassword()))
                .switchIfEmpty(Mono.error(new IllegalStateException("Contraseña incorrecta")));
    }

    public Mono<Usuario> register(RegisterUserDto registerDto) {
        return usuarioRepository.findByNombreOrEmail(registerDto.getEmail(), registerDto.getEmail())
                .flatMap(existingUser -> Mono.error(
                        new IllegalStateException("El usuario ya existe con el email: " + registerDto.getEmail())))
                .switchIfEmpty(Mono.defer(() -> {
                    Usuario nuevoUsuario = Usuario.builder()
                            .nombre(registerDto.getNombre())
                            .email(registerDto.getEmail())
                            .password(registerDto.getPassword())
                            .fechaRegistro(LocalDateTime.now())
                            .rol(Rol.ROL_USUARIO)
                            .build();
                    return usuarioRepository.save(nuevoUsuario);
                })).cast(Usuario.class);
    }

    public Mono<Boolean> validateUsuarioWithRole(String email, String password, String role) {
        return usuarioRepository.findByEmail(email)
                .filter(usuario -> usuario.getPassword().equals(password)
                        && usuario.getRol() == Rol.valueOf(role.toUpperCase()))
                .hasElement();
    }

    public Mono<Void> createUsuarioWithRole(String email, String password, String role) {
        Rol rol = Rol.valueOf(role.toUpperCase());
        Usuario usuario = Usuario.builder()
                .email(email)
                .password(password)
                .rol(rol)
                .build();
        return usuarioRepository.save(usuario).then();
    }
}
