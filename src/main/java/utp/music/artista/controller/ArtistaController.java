package utp.music.artista.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import utp.music.artista.model.Artista;
import utp.music.artista.model.dto.LoginArtistaDto;
import utp.music.artista.model.dto.RegisterArtistaDto;
import utp.music.artista.service.ArtistaService;
import utp.music.usuario.service.UsuarioService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;

    @PostMapping("/register")
    public Mono<Artista> registerArtista(@RequestBody @Valid RegisterArtistaDto registerDto) {
        return artistaService.registerArtista(registerDto);
    }

    @PostMapping("/login")
    public Mono<Artista> loginArtista(@RequestBody @Valid LoginArtistaDto loginDto) {
        return  artistaService.loginArtista(loginDto);
    }

    @GetMapping("/search")
    public Flux<Artista> searchArtistas(@RequestParam String nombreArtistico) {
        return artistaService.searchArtistas(nombreArtistico);
    }

    @GetMapping("/list")
    public Flux<Artista> listarArtistas() {
        return artistaService.listarArtistas();
    }
}
