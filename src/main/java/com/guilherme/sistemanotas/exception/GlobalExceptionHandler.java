package com.guilherme.sistemanotas.exception;

import com.guilherme.sistemanotas.dto.ErroRespostaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // =========================
    // RESPONSE STATUS EXCEPTION
    // =========================

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroRespostaDTO> tratarResponseStatusException(
            ResponseStatusException exception) {

        HttpStatus status =
                HttpStatus.valueOf(
                        exception.getStatusCode().value()
                );

        ErroRespostaDTO erro =
                new ErroRespostaDTO(
                        status.value(),
                        status.getReasonPhrase(),
                        exception.getReason(),
                        LocalDateTime.now()
                );

        return ResponseEntity
                .status(status)
                .body(erro);
    }

    // =========================
    // CONTENT-TYPE NÃO SUPORTADO
    // 415
    // =========================

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErroRespostaDTO> tratarMediaTypeNaoSuportado(
            HttpMediaTypeNotSupportedException exception) {

        HttpStatus status =
                HttpStatus.UNSUPPORTED_MEDIA_TYPE;

        ErroRespostaDTO erro =
                new ErroRespostaDTO(
                        status.value(),
                        status.getReasonPhrase(),
                        "Content-Type não suportado. Utilize application/json quando necessário.",
                        LocalDateTime.now()
                );

        return ResponseEntity
                .status(status)
                .body(erro);
    }

    // =========================
    // PARÂMETRO DE ROTA INVÁLIDO
    // 400
    // =========================

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroRespostaDTO> tratarParametroInvalido(
            MethodArgumentTypeMismatchException exception) {

        HttpStatus status =
                HttpStatus.BAD_REQUEST;

        ErroRespostaDTO erro =
                new ErroRespostaDTO(
                        status.value(),
                        status.getReasonPhrase(),
                        "Parâmetro inválido: " + exception.getName(),
                        LocalDateTime.now()
                );

        return ResponseEntity
                .status(status)
                .body(erro);
    }

    // =========================
    // ERROS DE VALIDAÇÃO
    // 400
    // =========================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDTO> tratarValidacao(
            MethodArgumentNotValidException exception) {

        HttpStatus status =
                HttpStatus.BAD_REQUEST;

        String mensagem =
                exception
                        .getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .findFirst()
                        .map(erro -> erro.getDefaultMessage())
                        .orElse("Dados inválidos");

        ErroRespostaDTO erro =
                new ErroRespostaDTO(
                        status.value(),
                        status.getReasonPhrase(),
                        mensagem,
                        LocalDateTime.now()
                );

        return ResponseEntity
                .status(status)
                .body(erro);
    }

    // =========================
    // ERROS INESPERADOS
    // 500
    // =========================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroRespostaDTO> tratarErroGenerico(
            Exception exception) {

        HttpStatus status =
                HttpStatus.INTERNAL_SERVER_ERROR;

        ErroRespostaDTO erro =
                new ErroRespostaDTO(
                        status.value(),
                        status.getReasonPhrase(),
                        "Ocorreu um erro interno no servidor",
                        LocalDateTime.now()
                );

        return ResponseEntity
                .status(status)
                .body(erro);
    }
}