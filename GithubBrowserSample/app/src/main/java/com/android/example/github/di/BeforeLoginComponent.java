package com.android.example.github.di;

import com.android.example.github.GithubApp;

import dagger.Subcomponent;

@BeforeLoginScope
@Subcomponent(modules = {BeforeLoginModule.class, ViewModelModule.class})
public interface BeforeLoginComponent {

    @Subcomponent.Builder
    interface Builder {
        BeforeLoginComponent build();
    }

    void inject(GithubApp githubApp);
}
