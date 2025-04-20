/**
 * TODO#5
 * Crea el repositorio para la entidad Token de modo que,
 * además de las operaciones CRUD, se pueda consultar el Token asociado
 * a un AppUser dado
 */

 package edu.comillas.icai.gitt.pat.spring.p5.repository;

 import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
 import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
 import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repositorio para Token.
 */
public interface TokenRepository extends CrudRepository<Token, String> {
    Optional<Token> findByAppUser(AppUser appUser);
}
