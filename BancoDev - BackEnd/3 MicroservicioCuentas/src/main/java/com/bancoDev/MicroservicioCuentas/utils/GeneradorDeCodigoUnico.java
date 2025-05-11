package com.bancoDev.MicroservicioCuentas.utils;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;


public class GeneradorDeCodigoUnico {

    private static String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static int longitud = 12;

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Set<String> generatedTexts = new HashSet<>();

    public static String generarCodigoUnico() {
        StringBuilder sb;
        do {
            sb = new StringBuilder(longitud);
            for (int i = 0; i < longitud; i++) {
                sb.append(caracteres.charAt(RANDOM.nextInt(caracteres.length())));
            }
        } while (generatedTexts.contains(sb.toString()));

        generatedTexts.add(sb.toString());
        return sb.toString();
    }

}
