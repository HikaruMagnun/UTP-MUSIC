package utp.music.artista.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import utp.music.artista.domain.port.out.UsuarioRepositoryPort;
import utp.music.usuario.domain.model.Usuario;
import utp.music.usuario.infrastructure.adapter.out.persistence.UsuarioJpaRepository;

@Component("artistaUsuarioRepositoryAdapter")
@RequiredArgsConstructor
public class ArtistaUsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository usuarioJpaRepository;

    @Override
    public Mono<Usuario> findByNombreOrEmail(String nombre, String email) {
        return usuarioJpaRepository.findByNombreOrEmail(nombre, email)
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Usuario> save(Usuario usuario) {
        return usuarioJpaRepository.save(toEntity(usuario))
                .map(this::toDomainModel);
    }

    private Usuario toDomainModel(utp.music.usuario.infrastructure.adapter.out.persistence.UsuarioEntity entity) {
        return Usuario.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .fechaRegistro(entity.getFechaRegistro())
                .idArtista(entity.getIdArtista())
                .rol(utp.music.usuario.domain.model.Rol.valueOf(entity.getRol().name()))
                .build();
    }

    private utp.music.usuario.infrastructure.adapter.out.persistence.UsuarioEntity toEntity(Usuario domain) {
        return utp.music.usuario.infrastructure.adapter.out.persistence.UsuarioEntity.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .fechaRegistro(domain.getFechaRegistro())
                .idArtista(domain.getIdArtista())
                .rol(utp.music.usuario.infrastructure.adapter.out.persistence.RolEntity.valueOf(domain.getRol().name()))
                .build();
    }
}
