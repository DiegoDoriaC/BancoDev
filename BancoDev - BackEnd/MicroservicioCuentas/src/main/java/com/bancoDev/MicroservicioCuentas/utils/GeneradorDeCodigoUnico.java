package com.bancoDev.MicroservicioCuentas.utils;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:generador.properties")
public class GeneradorDeCodigoUnico {

    @Value("${caracteres}")
    private static String caracteres;
    //private static String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Value("${longitud}")
    private static int longitud;
    //private static int longitud = 12;

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
