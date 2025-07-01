package utp.music.artista.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.artista.domain.model.Artista;
import utp.music.artista.domain.model.LoginArtistaDto;
import utp.music.artista.domain.model.RegisterArtistaDto;
import utp.music.artista.domain.port.in.GetArtistaUseCase;
import utp.music.artista.domain.port.in.ManageArtistaUseCase;

@Service
@RequiredArgsConstructor
public class ArtistaApplicationService {

    private final ManageArtistaUseCase manageArtistaUseCase;
    private final GetArtistaUseCase getArtistaUseCase;

    public Mono<Artista> registerArtista(RegisterArtistaDto registerDto) {
        return manageArtistaUseCase.registerArtista(registerDto);
    }

    public Mono<Artista> loginArtista(LoginArtistaDto loginDto) {
        return manageArtistaUseCase.loginArtista(loginDto);
    }

    public Flux<Artista> searchArtistas(String nombreArtistico) {
        return getArtistaUseCase.searchArtistas(nombreArtistico);
    }

    public Flux<Artista> listarArtistas() {
        return getArtistaUseCase.findAll();
    }

    public Mono<Artista> findById(Long id) {
        return getArtistaUseCase.findById(id);
    }
}
