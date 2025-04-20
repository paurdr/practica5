/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */

package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.p5.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private AppUserRepository userRepo;

    @Autowired
    private TokenRepository tokenRepo;

    @Autowired
    private Hashing hashing;

    @Override
    public ProfileResponse profile(RegisterRequest register) {
        // 1) hashear password
        String hashed = hashing.hash(register.password());
        // 2) crear entidad
        AppUser u = new AppUser();
        u.setName(register.name());
        u.setEmail(register.email());
        u.setRole(register.role());
        u.setPassword(hashed);
        // 3) guardar
        AppUser saved = userRepo.save(u);
        // 4) devolver DTO
        return new ProfileResponse(saved.getName(), saved.getEmail(), saved.getRole());
    }

    @Override
    public Token login(String email, String password) {
        // buscar usuario
        Optional<AppUser> ou = userRepo.findByEmail(email);
        if (ou.isEmpty()) return null;
        AppUser u = ou.get();
        // comparar contraseña sin cifrar vs cifrada
        if (!hashing.compare(u.getPassword(), password)) {
            return null;
        }
        // crear Token
        Token t = new Token();
        t.setAppUser(u);
        return tokenRepo.save(t);
    }

    @Override
    public AppUser authentication(String tokenId) {
        // buscar token
        return tokenRepo.findById(tokenId)
                .map(Token::getAppUser)
                .orElse(null);
    }

    @Override
    public ProfileResponse profile(AppUser appUser) {
        return new ProfileResponse(appUser.getName(), appUser.getEmail(), appUser.getRole());
    }

    @Override
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {
        // solo nombre y rol (email no cambian)
        appUser.setName(profile.name());
        appUser.setRole(profile.role());
        AppUser updated = userRepo.save(appUser);
        return new ProfileResponse(updated.getName(), updated.getEmail(), updated.getRole());
    }

    @Override
    public void logout(String tokenId) {
        tokenRepo.deleteById(tokenId);
    }

    @Override
    public void delete(AppUser appUser) {
        userRepo.delete(appUser);
    }
}