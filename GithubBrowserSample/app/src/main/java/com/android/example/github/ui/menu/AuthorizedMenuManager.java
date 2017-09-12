package com.android.example.github.ui.menu;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.example.github.GithubApp;
import com.android.example.github.MainActivity;
import com.android.example.github.R;
import com.android.example.github.auth.AuthParameters;
import com.android.example.github.ui.common.NavigationController;

public class AuthorizedMenuManager implements MenuManager {
    private final String loggedInUser;
    private final NavigationController navigationController;

    public AuthorizedMenuManager(AuthParameters parameters, NavigationController navigationController) {
        loggedInUser = parameters.login;
        this.navigationController = navigationController;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater, MainActivity activity) {
        inflater.inflate(R.menu.authorized, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item, MainActivity activity) {
        switch (item.getItemId()) {
            case R.id.logout:
                ((GithubApp) activity.getApplication()).setAuthorisation(null);
                return true;
            case R.id.private_details:
                navigationController.navigateToUser(loggedInUser);
                break;
        }
        return false;
    }
}
