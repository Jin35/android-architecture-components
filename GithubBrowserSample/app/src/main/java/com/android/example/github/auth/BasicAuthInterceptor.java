package com.android.example.github.auth;

import java.io.IOException;

import javax.annotation.Nullable;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

abstract class BasicAuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (chain.request().header("Authorization") != null) {
            return null; // Give up, we've already attempted to authenticate.
        }
        String credential = Credentials.basic(getLogin(), getPassword());
        return chain.proceed(chain.request().newBuilder()
                .header("Authorization", credential)
                .build());
    }

    abstract String getLogin();

    abstract String getPassword();
}
