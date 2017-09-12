package com.android.example.github.ui.menu;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.example.github.MainActivity;

public interface MenuManager {
    boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater, MainActivity activity);

    boolean onOptionsItemSelected(MenuItem item, MainActivity activity);
}
