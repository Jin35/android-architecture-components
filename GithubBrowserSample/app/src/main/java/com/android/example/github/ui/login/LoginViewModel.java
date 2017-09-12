package com.android.example.github.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.android.example.github.api.ApiResponse;
import com.android.example.github.api.AuthorizedGithubService;
import com.android.example.github.auth.AuthParameters;
import com.android.example.github.auth.MutableBasicAuthInterceptor;
import com.android.example.github.di.Authorized;
import com.android.example.github.repository.NetworkResource;
import com.android.example.github.vo.Repo;
import com.android.example.github.vo.Resource;

import java.util.List;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    private final MutableBasicAuthInterceptor authenticator;

    private final AuthorizedGithubService service;

    private AuthParameters parameters;

    private final NetworkResource<List<Repo>> networkResource = new NetworkResource<List<Repo>>() {
        @NonNull
        @Override
        protected LiveData<ApiResponse<List<Repo>>> createCall() {
            authenticator.setLogin(parameters.login);
            authenticator.setPassword(parameters.password);
            return service.getRepos(parameters.login);
        }
    };

    @Inject
    LoginViewModel(@Authorized AuthorizedGithubService service, MutableBasicAuthInterceptor
            authenticator) {
        this.authenticator = authenticator;
        this.service = service;
    }

    @MainThread
    void tryLogin(String login, String password) {
        parameters = new AuthParameters(login, password);
        networkResource.retry();
    }

    public LiveData<Resource<AuthParameters>> getLoginLiveData() {
        return Transformations.map(networkResource.asLiveData(), resource -> {
            switch (resource.status) {
                case ERROR:
                    return Resource.error(resource.message, parameters);
                case LOADING:
                    return Resource.loading(parameters);
                default:
                    return Resource.success(parameters);
            }
        });
    }
}
