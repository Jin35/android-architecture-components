package com.android.example.github.di;


import com.android.example.github.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AuthorizedActivityModule {
    @MainActivityScope
    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class,
            AuthorizedMainActivityModule.class})
    abstract MainActivity contributeMainActivity();
}
