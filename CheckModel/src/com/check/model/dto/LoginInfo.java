package com.check.model.dto;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Eugene on 13.02.14.
 */
public class LoginInfo implements Serializable {

    @Size(min = 1)
    private String login;

    @Size(min = 1)
    private String passwordHash;

    public LoginInfo() {

    }

    public LoginInfo(String login, String passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
