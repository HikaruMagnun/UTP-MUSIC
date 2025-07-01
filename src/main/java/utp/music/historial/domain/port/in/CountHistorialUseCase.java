package utp.music.historial.domain.port.in;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.historial.domain.model.DailyHistorialCount;

public interface CountHistorialUseCase {
    Mono<Long> countByArtistaId(Long artistaId);

    Flux<DailyHistorialCount> countByArtistaIdGroupByDay(Long artistaId);
}
