package edu.comillas.icai.gitt.pat.spring.p5.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO#7
 * Añade 2 tests unitarios adicionales que validen diferentes casos
 * (no variaciones del mismo caso) de registro con datos inválidos
 */

class RegisterRequestUnitTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidRequest() {
        // Given ...
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombre@email.com",
                Role.USER, "aaaaaaA1");
        // When ...
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registro);
        // Then ...
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidEmail() {
        // email mal formado
        RegisterRequest r = new RegisterRequest(
                "Nombre", "no-es-un-email", Role.USER, "Aa123456");
        Set<ConstraintViolation<RegisterRequest>> viol = validator.validate(r);
        assertEquals(1, viol.size());
        ConstraintViolation<RegisterRequest> v = viol.iterator().next();
        assertEquals("email", v.getPropertyPath().toString());
    }

    @Test
    public void testInvalidPassword() {
        // sin número o tamaño < 8
        RegisterRequest r = new RegisterRequest(
                "Nombre", "user@prueba.com", Role.USER, "short");
        Set<ConstraintViolation<RegisterRequest>> viol = validator.validate(r);
        assertEquals(1, viol.size());
        ConstraintViolation<RegisterRequest> v = viol.iterator().next();
        assertEquals("password", v.getPropertyPath().toString());
    }

}