package com.android.example.github.di;


import com.android.example.github.MainActivity;
import com.android.example.github.ui.common.NavigationController;
import com.android.example.github.ui.menu.BeforeLoginMenuManager;
import com.android.example.github.ui.menu.MenuManager;

import dagger.Module;
import dagger.Provides;

@Module
class BeforeLoginMainActivityModule {

    @MainActivityScope
    @Provides
    NavigationController provideNavigationController(MainActivity activity) {
        return new NavigationController(activity);
    }

    @MainActivityScope
    @Provides
    MenuManager provideMenuHandler() {
        return new BeforeLoginMenuManager();
    }
}
