package utp.music.artista.domain.port.in;

import reactor.core.publisher.Mono;
import utp.music.artista.domain.model.Artista;
import utp.music.artista.domain.model.LoginArtistaDto;
import utp.music.artista.domain.model.RegisterArtistaDto;

public interface ManageArtistaUseCase {
    Mono<Artista> registerArtista(RegisterArtistaDto registerDto);

    Mono<Artista> loginArtista(LoginArtistaDto loginDto);
}
