package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.model.Cart;
import it.progettosiw.pcexpress.model.Credentials;
import it.progettosiw.pcexpress.model.User;
import it.progettosiw.pcexpress.repository.CredentialsRepository;
import it.progettosiw.pcexpress.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private final CredentialsRepository credentialsRepository;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, CredentialsRepository credentialsRepository){
        this.userRepository = userRepository;
        this.credentialsRepository = credentialsRepository;
    }

    public void register(User user, String password){
        user.setCart(new Cart());
        userRepository.save(user);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encrypted_ps = encoder.encode(password);
        Credentials credentials = new Credentials(user.getEmail(), encrypted_ps, user);

        credentialsRepository.save(credentials);
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null || (authentication instanceof AnonymousAuthenticationToken)) {
            logger.error("utente non loggato");
            return null;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Optional<User> optUser = userRepository.findByEmail(userDetails.getUsername());
        if(!optUser.isPresent()){
            logger.error("utente corrente non trovato");
            return null;
        }
        return optUser.get();
    }

}
