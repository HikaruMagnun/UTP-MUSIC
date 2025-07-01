package utp.music.usuario.infrastructure.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import utp.music.usuario.application.service.UsuarioApplicationService;
import utp.music.usuario.domain.model.LoginUsuarioDto;
import utp.music.usuario.domain.model.RegisterUserDto;
import utp.music.usuario.domain.model.Usuario;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioApplicationService usuarioApplicationService;

    @PostMapping("/login")
    public Mono<Usuario> login(@RequestBody @Valid LoginUsuarioDto loginDto) {
        return usuarioApplicationService.login(loginDto);
    }

    @PostMapping("/register")
    public Mono<Usuario> register(@RequestBody @Valid RegisterUserDto registerDto) {
        System.out.println("Registering user: " + registerDto);
        return usuarioApplicationService.register(registerDto);
    }
}
