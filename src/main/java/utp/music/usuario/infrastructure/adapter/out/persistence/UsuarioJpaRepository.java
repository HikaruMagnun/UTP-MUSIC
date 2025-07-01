package utp.music.usuario.infrastructure.adapter.out.persistence;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface UsuarioJpaRepository extends R2dbcRepository<UsuarioEntity, Long> {
    Mono<UsuarioEntity> findByNombreOrEmail(String nombre, String email);

    Mono<UsuarioEntity> findByEmail(String email);
}
