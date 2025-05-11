package com.bancoDev.models.enums;

public enum PlazoMeses {

    TRES(3),
    SEIS(6),
    DOCE(12),
    TREINTA_Y_DOS(32),
    SESENTA(60);

    private final int valor;

    PlazoMeses(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

}
