package com.android.example.github.ui.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.android.example.github.GithubApp;
import com.android.example.github.R;
import com.android.example.github.databinding.LoginActivityBinding;
import com.android.example.github.di.Injectable;
import com.android.example.github.vo.Resource;
import com.android.example.github.vo.Status;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private LoginViewModel loginViewModel;

    private LoginActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
        binding.setLoginResource(Resource.success(null));
        binding.loginButton.setOnClickListener(this::login);
        loginViewModel.getLoginLiveData().observe(this, value -> {
            binding.setLoginResource(value);
            if (value.status == Status.SUCCESS) {
                ((GithubApp) getApplication()).setAuthorisation(value.data);
                finish();
            }
        });
    }

    public void login(View view) {
        final String login = binding.loginText.getText().toString();
        final String password = binding.passwordText.getText().toString();
        if (!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password)) {
            loginViewModel.tryLogin(login, password);
        } else {
            binding.setLoginResource(Resource.error("Empty login or password", null));
        }
    }
}
