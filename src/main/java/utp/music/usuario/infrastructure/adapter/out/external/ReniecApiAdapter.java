package utp.music.usuario.infrastructure.adapter.out.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.ReniecApiResponse;
import utp.music.usuario.domain.port.out.ReniecApiPort;

@Component
@RequiredArgsConstructor
public class ReniecApiAdapter implements ReniecApiPort {

    private final WebClient webClient;

    @Value("${reniec.api.token}")
    private String apiToken;

    @Value("${reniec.api.base-url:https://api.apis.net.pe/v2/reniec}")
    private String baseUrl;

    @Override
    public Mono<ReniecApiResponse> consultarDni(String dni) {
        return webClient.get()
                .uri(baseUrl + "/dni?numero=" + dni)
                .header("Authorization", "Bearer " + apiToken)
                .header("Accept", "application/json")
                .retrieve()
                .bodyToMono(ReniecApiResponse.class)
                .onErrorResume(ex -> {
                    // En caso de error, devolvemos una respuesta con mensaje de error
                    return Mono.just(ReniecApiResponse.builder()
                            .message("Error al consultar la API")
                            .build());
                });
    }
}
