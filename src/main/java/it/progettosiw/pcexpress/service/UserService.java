package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.model.Cart;
import it.progettosiw.pcexpress.model.Credentials;
import it.progettosiw.pcexpress.model.Sale;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
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

    @Transactional(readOnly = true)
    public User getUserById(Long user_id){
        Optional<User> optUser = userRepository.findById(user_id);
        if(!optUser.isPresent()){
            logger.error("utente non trovato");
            return null;
        }
        return optUser.get();
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }

    @Transactional
    public void updateCurrentUserInfo(String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber){
        User user = getCurrentUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }

    @Transactional  //non serve serializable perchè l'unicità della email è protetta già dal db
    public void register(User user, String password){
        user.setCart(new Cart());
        userRepository.save(user);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encrypted_ps = encoder.encode(password);
        Credentials credentials = new Credentials(user.getEmail(), encrypted_ps, user);

        credentialsRepository.save(credentials);
    }


    public UserDetails getCurrentUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null || (authentication instanceof AnonymousAuthenticationToken)) {
            logger.error("utente non loggato");
            return null;
        }
        return (UserDetails) authentication.getPrincipal();
    }

    @Transactional(readOnly = true)
    public User getCurrentUser(){
        UserDetails userDetails = getCurrentUserDetails();

        Optional<User> optUser = userRepository.findByEmail(userDetails.getUsername());
        if(!optUser.isPresent()){
            logger.error("utente corrente non trovato");
            return null;
        }
        return optUser.get();
    }

    public String getCurrentUserRole(){
        UserDetails userDetails = getCurrentUserDetails();
        return userDetails.getAuthorities().iterator().next().toString();
    }

}
