package com.android.example.github.di;

import com.android.example.github.GithubApp;
import com.android.example.github.auth.AuthParameters;

import dagger.BindsInstance;
import dagger.Subcomponent;

@AuthorizedScope
@Subcomponent(modules = {AuthorizedModule.class, ViewModelModule.class})
public interface AuthorizedComponent {
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        AuthorizedComponent.Builder authParameters(AuthParameters parameters);

        AuthorizedComponent build();
    }

    void inject(GithubApp githubApp);
}
