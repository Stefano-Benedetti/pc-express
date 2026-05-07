package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.model.Credentials;
import it.progettosiw.pcexpress.repository.CredentialsRepository;

public class CredentialsService {

    private CredentialsRepository credentialsRepository;

    public CredentialsService(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }
}
