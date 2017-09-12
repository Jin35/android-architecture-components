package com.android.example.github.api;

import android.arch.lifecycle.LiveData;

import com.android.example.github.vo.Repo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * This one should have all of the requests that require authorization.
 * This could use different response objects to account for additional fields available after
 * authorization.
 */

public interface AuthorizedGithubService {
    @GET("users/{login}/repos")
    LiveData<ApiResponse<List<Repo>>> getRepos(@Path("login") String login);
}
