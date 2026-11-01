package com.guilherme.sistemanotas.service;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class CalculoFrequenciaAcademicaTest {
    @Test void calculaFrequenciaPorDiasLetivos() {
        assertEquals(new BigDecimal("90.00"), CalculoFrequenciaAcademica.calcular(100, 10));
    }
    @Test void frequenciaNuncaFicaNegativa() {
        assertEquals(new BigDecimal("0.00"), CalculoFrequenciaAcademica.calcular(20, 25));
    }
    @Test void rejeitaDiasLetivosNaoConfigurados() {
        assertThrows(IllegalArgumentException.class, () -> CalculoFrequenciaAcademica.calcular(null, 0));
        assertThrows(IllegalArgumentException.class, () -> CalculoFrequenciaAcademica.calcular(0, 0));
    }
    @Test void rejeitaAusenciaNegativa() {
        assertThrows(IllegalArgumentException.class, () -> CalculoFrequenciaAcademica.calcular(20, -1));
    }
}
