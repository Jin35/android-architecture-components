package com.android.example.github.ui.menu;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.example.github.MainActivity;
import com.android.example.github.R;
import com.android.example.github.di.BeforeLoginScope;
import com.android.example.github.ui.login.LoginActivity;

import javax.inject.Inject;

@BeforeLoginScope
public class BeforeLoginMenuManager implements MenuManager {

    @Inject
    public BeforeLoginMenuManager() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater, MainActivity activity) {
        inflater.inflate(R.menu.before_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item, MainActivity activity) {
        if (item.getItemId() == R.id.login) {
            activity.startActivity(new Intent(activity, LoginActivity.class));
            return true;
        }
        return false;
    }
}
