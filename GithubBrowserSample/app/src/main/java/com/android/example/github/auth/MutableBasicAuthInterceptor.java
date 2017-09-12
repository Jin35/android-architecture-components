package com.android.example.github.auth;

import com.android.example.github.di.BeforeLoginScope;

import javax.inject.Inject;

/**
 * Authenticator used during authorization steps.
 */

@BeforeLoginScope
public class MutableBasicAuthInterceptor extends BasicAuthInterceptor {
    private String login;
    private String password;

    @Inject
    MutableBasicAuthInterceptor() {}

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    String getLogin() {
        return login;
    }

    @Override
    String getPassword() {
        return password;
    }
}
