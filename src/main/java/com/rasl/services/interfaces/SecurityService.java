package com.rasl.services.interfaces;

/**
 * Service for Security.
 *
 * @author Aslanov Ruslan
 * @version 1.0
 */
public interface SecurityService {
    String findLoggedUsername();

    void autoLogin(String username, String password);
}
