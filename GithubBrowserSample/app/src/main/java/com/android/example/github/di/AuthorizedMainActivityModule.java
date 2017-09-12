package com.android.example.github.di;

import com.android.example.github.MainActivity;
import com.android.example.github.auth.AuthParameters;
import com.android.example.github.ui.common.NavigationController;
import com.android.example.github.ui.menu.AuthorizedMenuManager;
import com.android.example.github.ui.menu.MenuManager;

import dagger.Module;
import dagger.Provides;

@Module
class AuthorizedMainActivityModule {
    @Provides
    @MainActivityScope
    NavigationController provideNavigationController(MainActivity activity) {
        return new NavigationController(activity);
    }

    @Provides
    @MainActivityScope
    MenuManager provideMenuHandler(AuthParameters authParameters, NavigationController controller) {
        return new AuthorizedMenuManager(authParameters, controller);
    }

}
