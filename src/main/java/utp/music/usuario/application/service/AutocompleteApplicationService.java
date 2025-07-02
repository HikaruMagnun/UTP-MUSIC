package utp.music.usuario.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.AutocompleteResponse;
import utp.music.usuario.domain.port.in.AutocompleteUseCase;

@Service
@RequiredArgsConstructor
public class AutocompleteApplicationService {

    private final AutocompleteUseCase autocompleteUseCase;

    public Mono<AutocompleteResponse> autocompletarPorDni(String dni) {
        return autocompleteUseCase.autocompletarPorDni(dni);
    }
}
