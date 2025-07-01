package utp.music.historial.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import utp.music.historial.domain.port.in.AddHistorialUseCase;
import utp.music.historial.domain.port.in.CountHistorialUseCase;
import utp.music.historial.domain.port.in.GetHistorialUseCase;
import utp.music.historial.domain.port.out.HistorialRepositoryPort;
import utp.music.historial.domain.service.HistorialService;

@Configuration
public class HistorialConfig {

    @Bean
    public HistorialService historialService(HistorialRepositoryPort historialRepositoryPort) {
        return new HistorialService(historialRepositoryPort);
    }

    @Bean
    public AddHistorialUseCase addHistorialUseCase(HistorialService historialService) {
        return historialService;
    }

    @Bean
    public GetHistorialUseCase getHistorialUseCase(HistorialService historialService) {
        return historialService;
    }

    @Bean
    public CountHistorialUseCase countHistorialUseCase(HistorialService historialService) {
        return historialService;
    }
}
