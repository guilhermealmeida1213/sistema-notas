package com.guilherme.sistemanotas.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Configuration
public class SecurityConfig {

        private final JwtAuthFilter jwtAuthFilter;

        public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
                this.jwtAuthFilter = jwtAuthFilter;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http)
                throws Exception {

                http
                        .csrf(csrf -> csrf.disable())

                        .sessionManagement(session ->
                                session.sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS
                                )
                        )

                        .exceptionHandling(exception -> exception

                                .authenticationEntryPoint(
                                        (request, response, authException) ->
                                                escreverErroSeguranca(
                                                        response,
                                                        HttpServletResponse.SC_UNAUTHORIZED,
                                                        "Unauthorized",
                                                        "Não autenticado"
                                                )
                                )

                                .accessDeniedHandler(
                                        (request, response, accessDeniedException) ->
                                                escreverErroSeguranca(
                                                        response,
                                                        HttpServletResponse.SC_FORBIDDEN,
                                                        "Forbidden",
                                                        "Acesso negado"
                                                )
                                )
                        )

                        .authorizeHttpRequests(auth -> auth

                                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/swagger-ui.html",
                                        "/v3/api-docs/**"
                                ).permitAll()

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/",
                                        "/login",
                                        "/login.html",
                                        "/aluno.html",
                                        "/professor.html",
                                        "/admin.html"
                                ).permitAll()

                                .requestMatchers(
                                        "/css/**"
                                ).permitAll()

                                .requestMatchers(
                                        "/js/**"
                                ).permitAll()

                                .requestMatchers(
                                        "/error"
                                ).permitAll()

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/usuarios/login"
                                ).permitAll()

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/senha/esqueci",
                                        "/senha/redefinir"
                                ).permitAll()

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/esqueci-senha.html",
                                        "/redefinir-senha.html"
                                ).permitAll()

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/usuarios"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/usuarios/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/usuarios/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/usuarios/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/alunos"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/alunos/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/alunos/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/professores"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/professores/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/professores/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/professores/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/disciplinas"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/disciplinas/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/disciplinas/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/disciplinas/**"
                                ).hasAnyRole(
                                        "ALUNO",
                                        "PROFESSOR",
                                        "ADMINISTRADOR"
                                )

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/turmas"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/turmas/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/turmas/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/turmas/**"
                                ).hasAnyRole(
                                        "ALUNO",
                                        "PROFESSOR",
                                        "ADMINISTRADOR"
                                )

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/turmas-disciplinas/minhas"
                                ).hasRole("PROFESSOR")

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/turmas-disciplinas"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/turmas-disciplinas/*/professor"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/turmas-disciplinas/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/turmas-disciplinas/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/matriculas/minhas"
                                ).hasRole("ALUNO")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/matriculas/turma/**"
                                ).hasRole("PROFESSOR")

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/matriculas"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/matriculas/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.DELETE,
                                        "/matriculas/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/matriculas/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/notas/minhas"
                                ).hasRole("ALUNO")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/notas/minhas-medias"
                                ).hasRole("ALUNO")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/notas/meu-resumo"
                                ).hasRole("ALUNO")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/notas/minhas-turmas"
                                ).hasRole("PROFESSOR")

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/notas/avi"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/notas/avi/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/notas"
                                ).hasAnyRole(
                                        "PROFESSOR",
                                        "ADMINISTRADOR"
                                )

                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/notas/**"
                                ).hasAnyRole(
                                        "PROFESSOR",
                                        "ADMINISTRADOR"
                                )

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/notas"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/notas/id/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/faltas/minhas"
                                ).hasRole("ALUNO")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/faltas/minhas-turmas"
                                ).hasRole("PROFESSOR")

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/faltas"
                                ).hasAnyRole(
                                        "PROFESSOR",
                                        "ADMINISTRADOR"
                                )

                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "/faltas/**"
                                ).hasAnyRole(
                                        "PROFESSOR",
                                        "ADMINISTRADOR"
                                )

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/faltas"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/faltas/id/**"
                                ).hasRole("ADMINISTRADOR")

                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/assistente/perguntar"
                                ).hasRole("ALUNO")

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/auditorias"
                                ).hasRole("ADMINISTRADOR")

                                .anyRequest().authenticated()
                        )

                        .addFilterBefore(
                                jwtAuthFilter,
                                UsernamePasswordAuthenticationFilter.class
                        );

                return http.build();
        }

        private void escreverErroSeguranca(
                HttpServletResponse response,
                int status,
                String erro,
                String mensagem) throws IOException {

                response.setStatus(status);

                response.setContentType(
                        "application/json"
                );

                response.setCharacterEncoding(
                        "UTF-8"
                );

                String json = """
                {
                  "status": %d,
                  "erro": "%s",
                  "mensagem": "%s",
                  "dataHora": "%s"
                }
                """.formatted(
                        status,
                        erro,
                        mensagem,
                        LocalDateTime.now()
                );

                response
                        .getWriter()
                        .write(json);
        }
}