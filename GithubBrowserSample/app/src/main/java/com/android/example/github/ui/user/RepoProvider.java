package com.android.example.github.ui.user;

import android.arch.lifecycle.LiveData;

import com.android.example.github.vo.Repo;
import com.android.example.github.vo.Resource;

import java.util.List;

public interface RepoProvider {
    LiveData<Resource<List<Repo>>> loadRepos(String owner);
}
