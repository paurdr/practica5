package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    AppUserRepository appUserRepository;

    /**
     * TODO#9
     * Completa este test de integración para que verifique
     * que los repositorios TokenRepository y AppUserRepository guardan
     * los datos correctamente, y las consultas por AppToken y por email
     * definidas respectivamente en ellos retornan el token y usuario guardados.
     */
    @Test
    void saveTest() {
        // Given
        AppUser u = new AppUser();
        u.setName("X");
        u.setEmail("x@x.com");
        u.setPassword("p");
        u.setRole(Role.USER);
        u = appUserRepository.save(u);

        Token t = new Token();
        t.setAppUser(u);
        t = tokenRepository.save(t);

        // When
        java.util.Optional<AppUser> ou = appUserRepository.findByEmail("x@x.com");
        java.util.Optional<Token> ot = tokenRepository.findByAppUser(u);

        // Then
        assertTrue(ou.isPresent());
        assertEquals(u.getId(), ou.get().getId());
        assertTrue(ot.isPresent());
        assertEquals(t.getId(), ot.get().getId());
    }

    /**
     * TODO#10
     * Completa este test de integración para que verifique que
     * cuando se borra un usuario, automáticamente se borran sus tokens asociados.
     */
    @Test
    void deleteCascadeTest() {
        // Given
        AppUser u = new AppUser();
        u.setName("Y");
        u.setEmail("y@y.com");
        u.setPassword("p");
        u.setRole(Role.USER);
        u = appUserRepository.save(u);

        Token t = new Token();
        t.setAppUser(u);
        tokenRepository.save(t);

        // When
        appUserRepository.delete(u);

        // Then
        assertEquals(0, appUserRepository.count());
        assertEquals(0, tokenRepository.count()); // ¡el token debe irse también!
    }
}