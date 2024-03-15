package ru.otus.highload.config;

import static org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class Pbkdf2PasswordEncoderConfig {

    @Bean
    public Pbkdf2PasswordEncoder pbkdf2PasswordEncoder() {
        String pepper = "pepperyol56#gfcgjhn"; // secret key used by password encoding
        int iterations = 200000;  // number of hash iteration
        int hashWidth = 256;      // hash width in bits

        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder =
            new Pbkdf2PasswordEncoder(pepper, iterations, hashWidth, PBKDF2WithHmacSHA256);
        pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
        return pbkdf2PasswordEncoder;
    }
}
