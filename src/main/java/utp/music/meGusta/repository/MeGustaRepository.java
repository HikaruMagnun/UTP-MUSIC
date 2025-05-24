package utp.music.meGusta.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.meGusta.model.MeGusta;

@Repository
public interface MeGustaRepository extends ReactiveCrudRepository<MeGusta, Object> {

    Flux<MeGusta> findByUsuarioId(Long usuarioId);

    Mono<Void> deleteByUsuarioIdAndCancionId(Long usuarioId, Long cancionId);
}
