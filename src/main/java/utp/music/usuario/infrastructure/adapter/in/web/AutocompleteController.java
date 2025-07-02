package utp.music.usuario.infrastructure.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import utp.music.usuario.application.service.AutocompleteApplicationService;
import utp.music.usuario.domain.model.AutocompleteResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/autocomplete")
public class AutocompleteController {

    private final AutocompleteApplicationService autocompleteApplicationService;

    @GetMapping("/dni")
    public Mono<AutocompleteResponse> autocompletarPorDni(@RequestParam String dni) {
        return autocompleteApplicationService.autocompletarPorDni(dni);
    }
}
