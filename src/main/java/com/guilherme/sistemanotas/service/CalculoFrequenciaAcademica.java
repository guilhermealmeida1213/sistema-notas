package com.guilherme.sistemanotas.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Regras puras de frequência calculada por aulas previstas. */
public final class CalculoFrequenciaAcademica {
    private CalculoFrequenciaAcademica() { }

    public static BigDecimal calcular(Integer aulasPrevistas, Integer aulasAusencia) {
        if (aulasPrevistas == null || aulasPrevistas <= 0) {
            throw new IllegalArgumentException("O total de aulas previstas deve ser configurado e maior que zero.");
        }
        if (aulasAusencia == null || aulasAusencia < 0) {
            throw new IllegalArgumentException("A quantidade de aulas perdidas não pode ser negativa.");
        }
        int aulasPresentes = Math.max(0, aulasPrevistas - aulasAusencia);
        return BigDecimal.valueOf(aulasPresentes)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(aulasPrevistas), 2, RoundingMode.HALF_UP);
    }
}
