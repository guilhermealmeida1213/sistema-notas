package com.guilherme.sistemanotas.config;

import com.guilherme.sistemanotas.model.Usuario;
import com.guilherme.sistemanotas.repository.UsuarioRepository;
import com.guilherme.sistemanotas.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    public JwtAuthFilter(JwtService jwtService, UsuarioRepository usuarioRepository) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtService.tokenValido(token)) {
                try {
                    String email = jwtService.extrairEmail(token);
                    Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
                    if (usuario != null && Boolean.TRUE.equals(usuario.getAtivo())) {
                        var autoridade = new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name());
                        var autenticacao = new UsernamePasswordAuthenticationToken(usuario.getEmail(), null, List.of(autoridade));
                        SecurityContextHolder.getContext().setAuthentication(autenticacao);
                    } else {
                        SecurityContextHolder.clearContext();
                    }
                } catch (Exception ignored) {
                    SecurityContextHolder.clearContext();
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}