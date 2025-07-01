package utp.music.historial.infrastructure.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.historial.application.service.HistorialApplicationService;
import utp.music.historial.domain.model.DailyHistorialCount;
import utp.music.historial.domain.model.HistorialReproduccion;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor
public class HistorialController {

    private final HistorialApplicationService historialApplicationService;

    @PostMapping("/add")
    public Mono<String> addToHistorial(@RequestParam Long idUsuario, @RequestParam Long idCancion) {
        return historialApplicationService.addToHistorial(idUsuario, idCancion)
                .thenReturn("Historial updated successfully");
    }

    @GetMapping("/user")
    public Flux<HistorialReproduccion> getHistorialByUsuario(@RequestParam Long idUsuario) {
        return historialApplicationService.getHistorialByUsuario(idUsuario);
    }

    @GetMapping("/artist-count")
    public Mono<Long> countHistorialByArtista(@RequestParam Long idArtista) {
        return historialApplicationService.countHistorialByArtista(idArtista);
    }

    @GetMapping("/artist-count-by-day")
    public Flux<DailyHistorialCount> countHistorialByArtistaGroupByDay(@RequestParam Long idArtista) {
        return historialApplicationService.countHistorialByArtistaGroupByDay(idArtista);
    }
}
