package utp.music.artista.infrastructure.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.artista.application.service.ArtistaApplicationService;
import utp.music.artista.domain.model.Artista;
import utp.music.artista.domain.model.LoginArtistaDto;
import utp.music.artista.domain.model.RegisterArtistaDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    private final ArtistaApplicationService artistaApplicationService;

    @PostMapping("/register")
    public Mono<Artista> registerArtista(@RequestBody @Valid RegisterArtistaDto registerDto) {
        return artistaApplicationService.registerArtista(registerDto);
    }

    @PostMapping("/login")
    public Mono<Artista> loginArtista(@RequestBody @Valid LoginArtistaDto loginDto) {
        return artistaApplicationService.loginArtista(loginDto);
    }

    @GetMapping("/search")
    public Flux<Artista> searchArtistas(@RequestParam String nombreArtistico) {
        return artistaApplicationService.searchArtistas(nombreArtistico);
    }

    @GetMapping("/list")
    public Flux<Artista> listarArtistas() {
        return artistaApplicationService.listarArtistas();
    }
}
