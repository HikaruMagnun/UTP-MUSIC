package utp.music.historial.domain.port.in;

import reactor.core.publisher.Mono;

public interface AddHistorialUseCase {
    Mono<Void> addToHistorial(Long usuarioId, Long cancionId);
}
