package com.android.example.github.auth;

import com.android.example.github.di.AuthorizedScope;

import javax.inject.Inject;

/**
 * Once authorisation happens, auth parameters are immutable
 */
@AuthorizedScope
public class AuthorizedAuthInterceptor extends BasicAuthInterceptor {
    public final AuthParameters authParameters;

    @Inject
    public AuthorizedAuthInterceptor(AuthParameters authParameters) {
        this.authParameters = authParameters;
    }

    @Override
    String getLogin() {
        return authParameters.login;
    }

    @Override
    String getPassword() {
        return authParameters.password;
    }
}
