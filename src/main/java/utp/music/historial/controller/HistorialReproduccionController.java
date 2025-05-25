package utp.music.historial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.historial.model.HistorialReproduccion;
import utp.music.historial.service.HistorialReproduccionService;

@RestController
@RequestMapping("/api/historial")
public class HistorialReproduccionController {

    @Autowired
    private HistorialReproduccionService historialReproduccionService;

    @PostMapping("/add")
    public Mono<String> addToHistorial(@RequestParam Long idUsuario, @RequestParam Long idCancion) {
        return historialReproduccionService.addToHistorial(idUsuario, idCancion)
                .thenReturn("Historial updated successfully");
    }

    @GetMapping("/user")
    public Flux<HistorialReproduccion> getHistorialByUsuario(@RequestParam Long idUsuario) {
        return historialReproduccionService.findTop20ByUsuarioId(idUsuario);
    }

    @GetMapping("/artist-count")
    public Mono<Long> countHistorialByArtista(@RequestParam Long idArtista) {
        return historialReproduccionService.countByArtistaId(idArtista);
    }
}
