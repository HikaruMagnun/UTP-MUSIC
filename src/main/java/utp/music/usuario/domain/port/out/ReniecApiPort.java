package utp.music.usuario.domain.port.out;

import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.ReniecApiResponse;

public interface ReniecApiPort {
    Mono<ReniecApiResponse> consultarDni(String dni);
}
