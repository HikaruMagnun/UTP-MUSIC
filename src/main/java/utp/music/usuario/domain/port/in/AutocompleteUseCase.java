package utp.music.usuario.domain.port.in;

import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.AutocompleteResponse;

public interface AutocompleteUseCase {
    Mono<AutocompleteResponse> autocompletarPorDni(String dni);
}
