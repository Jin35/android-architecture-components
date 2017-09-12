package com.android.example.github.di;

import com.android.example.github.api.AuthorizedGithubService;
import com.android.example.github.api.GithubService;
import com.android.example.github.auth.MutableBasicAuthInterceptor;
import com.android.example.github.repository.RepoRepository;
import com.android.example.github.ui.user.BeforeLoginRepoProvider;
import com.android.example.github.ui.user.RepoProvider;
import com.android.example.github.util.LiveDataCallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = BeforeLoginActivityModule.class)
class BeforeLoginModule {

    @Provides
    @BeforeLoginScope
    @Authorized
    OkHttpClient provideOkHttpClient(MutableBasicAuthInterceptor authInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(authInterceptor).build();
    }

    @Provides
    @BeforeLoginScope
    @Authorized
    AuthorizedGithubService provideGithubService(@Authorized OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(AuthorizedGithubService.class);
    }

    @Provides
    @BeforeLoginScope
    RepoProvider provideRepoRepository(RepoRepository repoRepository) {
        return new BeforeLoginRepoProvider(repoRepository);
    }
}
