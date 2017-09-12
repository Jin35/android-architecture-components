package com.android.example.github.ui.user;


import android.arch.lifecycle.LiveData;

import com.android.example.github.repository.RepoRepository;
import com.android.example.github.vo.Repo;
import com.android.example.github.vo.Resource;

import java.util.List;

public class BeforeLoginRepoProvider implements RepoProvider {
    private final RepoRepository repoRepository;

    public BeforeLoginRepoProvider(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    @Override
    public LiveData<Resource<List<Repo>>> loadRepos(String owner) {
        return repoRepository.loadRepos(owner);
    }
}
