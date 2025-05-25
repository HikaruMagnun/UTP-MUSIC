package utp.music.meGusta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.cancion.model.Cancion;
import utp.music.meGusta.service.MeGustaService;

@RestController
@RequestMapping("/api/me-gusta")
public class MeGustaController {

    @Autowired
    private MeGustaService meGustaService;

    @PostMapping("/add")
    public Mono<String> addMeGusta(@RequestParam Long idUsuario, @RequestParam Long idCancion) {
        return meGustaService.addMeGusta(idUsuario, idCancion).thenReturn("Me gusta added successfully");
    }

    @DeleteMapping("/remove")
    public Mono<String> removeMeGusta(@RequestParam Long idUsuario, @RequestParam Long idCancion) {
        return meGustaService.removeMeGusta(idUsuario, idCancion).thenReturn("Me gusta removed successfully");
    }

    @GetMapping("/list")
    public Flux<Cancion> listMeGusta(@RequestParam Long idUsuario) {
        return meGustaService.findAllByUsuarioId(idUsuario);
    }
}
