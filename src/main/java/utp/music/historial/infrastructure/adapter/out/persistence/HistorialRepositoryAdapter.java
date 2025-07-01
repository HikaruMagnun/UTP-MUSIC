package utp.music.historial.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.historial.domain.model.DailyHistorialCount;
import utp.music.historial.domain.model.HistorialReproduccion;
import utp.music.historial.domain.port.out.HistorialRepositoryPort;

@Component
@RequiredArgsConstructor
public class HistorialRepositoryAdapter implements HistorialRepositoryPort {

    private final HistorialJpaRepository historialJpaRepository;

    @Override
    public Flux<HistorialReproduccion> findAll() {
        return historialJpaRepository.findAll()
                .map(this::toDomainModel);
    }

    @Override
    public Mono<HistorialReproduccion> findById(Long id) {
        return historialJpaRepository.findById(id)
                .map(this::toDomainModel);
    }

    @Override
    public Flux<HistorialReproduccion> findByUsuarioId(Long usuarioId) {
        return historialJpaRepository.findByUsuarioId(usuarioId)
                .map(this::toDomainModel);
    }

    @Override
    public Flux<HistorialReproduccion> findByUsuarioIdOrderByFechaHoraDesc(Long usuarioId) {
        return historialJpaRepository.findByUsuarioIdOrderByFechaHoraDesc(usuarioId)
                .map(this::toDomainModel);
    }

    @Override
    public Mono<HistorialReproduccion> save(HistorialReproduccion historial) {
        HistorialEntity entity = toEntity(historial);
        return historialJpaRepository.save(entity)
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return historialJpaRepository.deleteById(id);
    }

    @Override
    public Mono<Long> countByArtistaId(Long artistaId) {
        return historialJpaRepository.countByArtistaId(artistaId);
    }

    @Override
    public Flux<DailyHistorialCount> countByArtistaIdGroupByDay(Long artistaId) {
        return historialJpaRepository.countByArtistaIdGroupByDay(artistaId);
    }

    private HistorialReproduccion toDomainModel(HistorialEntity entity) {
        return HistorialReproduccion.builder()
                .id(entity.getId())
                .usuarioId(entity.getUsuarioId())
                .cancionId(entity.getCancionId())
                .fechaHora(entity.getFechaHora())
                .build();
    }

    private HistorialEntity toEntity(HistorialReproduccion domain) {
        return HistorialEntity.builder()
                .id(domain.getId())
                .usuarioId(domain.getUsuarioId())
                .cancionId(domain.getCancionId())
                .fechaHora(domain.getFechaHora())
                .build();
    }
}
