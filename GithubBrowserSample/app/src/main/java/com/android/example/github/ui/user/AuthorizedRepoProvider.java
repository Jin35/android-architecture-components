package com.android.example.github.ui.user;


import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.android.example.github.api.ApiResponse;
import com.android.example.github.api.AuthorizedGithubService;
import com.android.example.github.auth.AuthParameters;
import com.android.example.github.di.Authorized;
import com.android.example.github.repository.NetworkResource;
import com.android.example.github.repository.RepoRepository;
import com.android.example.github.vo.Repo;
import com.android.example.github.vo.Resource;

import java.util.List;

/**
 * This class shows private repos for logged in user and disables persistence for them.
 * For every other user it shows only public repos and persists them to room.
 */
public class AuthorizedRepoProvider implements RepoProvider {

    private final RepoRepository repoRepository;

    private final AuthorizedGithubService githubService;

    private final String loggedInUser;

    private final NetworkResource<List<Repo>> networkResource = new NetworkResource<List<Repo>>() {
        @NonNull
        @Override
        protected LiveData<ApiResponse<List<Repo>>> createCall() {
            return githubService.getRepos(loggedInUser);
        }
    };

    public AuthorizedRepoProvider(RepoRepository repoRepository, AuthParameters authParameters,
                                  @Authorized AuthorizedGithubService githubService) {
        this.repoRepository = repoRepository;
        this.githubService = githubService;
        loggedInUser = authParameters.login;
    }

    @Override
    public LiveData<Resource<List<Repo>>> loadRepos(String owner) {
        if (loggedInUser.equals(owner)) {
            networkResource.retry();
            return networkResource.asLiveData();
        }
        return repoRepository.loadRepos(owner);
    }
}
