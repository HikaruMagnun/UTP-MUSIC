package utp.music.historial.domain.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.historial.domain.model.DailyHistorialCount;
import utp.music.historial.domain.model.HistorialReproduccion;
import utp.music.historial.domain.port.in.AddHistorialUseCase;
import utp.music.historial.domain.port.in.CountHistorialUseCase;
import utp.music.historial.domain.port.in.GetHistorialUseCase;
import utp.music.historial.domain.port.out.HistorialRepositoryPort;

@Service
@RequiredArgsConstructor
public class HistorialService implements AddHistorialUseCase, GetHistorialUseCase, CountHistorialUseCase {

    private final HistorialRepositoryPort historialRepositoryPort;

    @Override
    public Mono<Void> addToHistorial(Long usuarioId, Long cancionId) {
        HistorialReproduccion historial = HistorialReproduccion.create(usuarioId, cancionId);
        return historialRepositoryPort.save(historial).then();
    }

    @Override
    public Flux<HistorialReproduccion> findAll() {
        return historialRepositoryPort.findAll();
    }

    @Override
    public Mono<HistorialReproduccion> findById(Long id) {
        return historialRepositoryPort.findById(id);
    }

    @Override
    public Flux<HistorialReproduccion> findByUsuarioId(Long usuarioId) {
        return historialRepositoryPort.findByUsuarioId(usuarioId);
    }

    @Override
    public Flux<HistorialReproduccion> findTop20ByUsuarioId(Long usuarioId) {
        return historialRepositoryPort.findByUsuarioIdOrderByFechaHoraDesc(usuarioId).take(20);
    }

    @Override
    public Mono<Long> countByArtistaId(Long artistaId) {
        return historialRepositoryPort.countByArtistaId(artistaId);
    }

    @Override
    public Flux<DailyHistorialCount> countByArtistaIdGroupByDay(Long artistaId) {
        return historialRepositoryPort.countByArtistaIdGroupByDay(artistaId);
    }
}
