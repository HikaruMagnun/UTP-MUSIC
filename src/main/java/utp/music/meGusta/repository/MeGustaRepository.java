package utp.music.meGusta.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.cancion.model.Cancion;
import utp.music.meGusta.model.MeGusta;

@Repository
public interface MeGustaRepository extends ReactiveCrudRepository<MeGusta, Object> {

    @Query("SELECT c.* FROM cancion c JOIN me_gusta m ON c.id = m.cancion_id WHERE m.usuario_id = :usuarioId")
    Flux<Cancion> findCancionesByUsuarioId(Long usuarioId);

    Mono<Void> deleteByUsuarioIdAndCancionId(Long usuarioId, Long cancionId);
}
