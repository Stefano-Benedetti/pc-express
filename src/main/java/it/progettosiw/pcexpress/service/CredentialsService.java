package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.model.Credentials;
import it.progettosiw.pcexpress.repository.CredentialsRepository;

public class CredentialsService {

    private CredentialsRepository credentialsRepository;
    private UserService userService;

    public CredentialsService(CredentialsRepository credentialsRepository, UserService userService) {
        this.credentialsRepository = credentialsRepository;
        this.userService = userService;
    }

}
