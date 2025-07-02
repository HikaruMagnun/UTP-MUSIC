package utp.music.usuario.domain.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.AutocompleteResponse;
import utp.music.usuario.domain.port.in.AutocompleteUseCase;
import utp.music.usuario.domain.port.out.ReniecApiPort;

@Service
@RequiredArgsConstructor
public class AutocompleteService implements AutocompleteUseCase {

    private final ReniecApiPort reniecApiPort;

    @Override
    public Mono<AutocompleteResponse> autocompletarPorDni(String dni) {
        // Validación básica del DNI
        if (dni == null || dni.trim().isEmpty() || dni.length() != 8 || !dni.matches("\\d+")) {
            return Mono.just(AutocompleteResponse.invalid(dni));
        }

        return reniecApiPort.consultarDni(dni)
                .map(response -> {
                    if (response.getMessage() != null) {
                        if (response.getMessage().contains("not found")) {
                            return AutocompleteResponse.notFound(dni);
                        } else if (response.getMessage().contains("dni no valido")) {
                            return AutocompleteResponse.invalid(dni);
                        } else {
                            return AutocompleteResponse.builder()
                                    .dni(dni)
                                    .found(false)
                                    .message(response.getMessage())
                                    .build();
                        }
                    } else if (response.getNombreCompleto() != null) {
                        return AutocompleteResponse.success(dni, response.getNombreCompleto());
                    } else {
                        return AutocompleteResponse.notFound(dni);
                    }
                })
                .onErrorReturn(AutocompleteResponse.builder()
                        .dni(dni)
                        .found(false)
                        .message("Error al consultar el DNI")
                        .build());
    }
}
