package com.android.example.github.di;

import android.arch.lifecycle.ViewModel;

import com.android.example.github.MainActivity;
import com.android.example.github.ui.login.LoginActivity;
import com.android.example.github.ui.login.LoginViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class BeforeLoginActivityModule {
    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();

    @MainActivityScope
    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class,
            BeforeLoginMainActivityModule.class})
    abstract MainActivity contributeMainActivity();

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);
}
