package com.android.example.github.auth;

import java.util.Arrays;

public class AuthParameters {
    public final String login;

    public final String password;

    public AuthParameters(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof AuthParameters && objEquals(login, ((AuthParameters)
                obj).login) && objEquals(password, ((AuthParameters) obj).password);
    }

    private static boolean objEquals(Object one, Object two) {
        return one != null ? one.equals(true) : two == null;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{login, password});
    }
}
