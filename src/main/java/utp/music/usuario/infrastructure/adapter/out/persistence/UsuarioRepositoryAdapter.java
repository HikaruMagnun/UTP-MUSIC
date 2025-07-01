package utp.music.usuario.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.Rol;
import utp.music.usuario.domain.model.Usuario;
import utp.music.usuario.domain.port.out.UsuarioRepositoryPort;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository usuarioJpaRepository;

    @Override
    public Flux<Usuario> findAll() {
        return usuarioJpaRepository.findAll()
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Usuario> findById(Long id) {
        return usuarioJpaRepository.findById(id)
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Usuario> save(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);
        return usuarioJpaRepository.save(entity)
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return usuarioJpaRepository.deleteById(id);
    }

    @Override
    public Mono<Usuario> findByNombreOrEmail(String nombre, String email) {
        return usuarioJpaRepository.findByNombreOrEmail(nombre, email)
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Usuario> findByEmail(String email) {
        return usuarioJpaRepository.findByEmail(email)
                .map(this::toDomainModel);
    }

    private Usuario toDomainModel(UsuarioEntity entity) {
        return Usuario.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .fechaRegistro(entity.getFechaRegistro())
                .idArtista(entity.getIdArtista())
                .rol(Rol.valueOf(entity.getRol().name()))
                .build();
    }

    private UsuarioEntity toEntity(Usuario domain) {
        return UsuarioEntity.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .fechaRegistro(domain.getFechaRegistro())
                .idArtista(domain.getIdArtista())
                .rol(RolEntity.valueOf(domain.getRol().name()))
                .build();
    }
}
