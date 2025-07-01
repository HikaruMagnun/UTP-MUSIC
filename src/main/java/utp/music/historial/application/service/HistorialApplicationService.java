package utp.music.historial.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.historial.domain.model.DailyHistorialCount;
import utp.music.historial.domain.model.HistorialReproduccion;
import utp.music.historial.domain.port.in.AddHistorialUseCase;
import utp.music.historial.domain.port.in.CountHistorialUseCase;
import utp.music.historial.domain.port.in.GetHistorialUseCase;

@Service
@RequiredArgsConstructor
public class HistorialApplicationService {

    private final AddHistorialUseCase addHistorialUseCase;
    private final GetHistorialUseCase getHistorialUseCase;
    private final CountHistorialUseCase countHistorialUseCase;

    public Mono<Void> addToHistorial(Long usuarioId, Long cancionId) {
        return addHistorialUseCase.addToHistorial(usuarioId, cancionId);
    }

    public Flux<HistorialReproduccion> getHistorialByUsuario(Long usuarioId) {
        return getHistorialUseCase.findTop20ByUsuarioId(usuarioId);
    }

    public Mono<Long> countHistorialByArtista(Long artistaId) {
        return countHistorialUseCase.countByArtistaId(artistaId);
    }

    public Flux<DailyHistorialCount> countHistorialByArtistaGroupByDay(Long artistaId) {
        return countHistorialUseCase.countByArtistaIdGroupByDay(artistaId);
    }
}
