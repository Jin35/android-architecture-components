/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.example.github;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.android.example.github.auth.AuthParameters;
import com.android.example.github.di.AppInjector;
import com.android.example.github.di.AuthorizedComponent;
import com.android.example.github.di.BeforeLoginComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;


public class GithubApp extends Application implements HasActivityInjector {
    public static final String REINJECT_INTENT_ACTION = "reinject_intent_action";

    @Inject
    BeforeLoginComponent.Builder beforeloginComponentBuilder;

    @Inject
    AuthorizedComponent.Builder authorizedComponentBuilder;

    private AuthParameters parameters;

    private boolean initialInjectHappened = false;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        AppInjector.init(this);

    }

    public void setAuthorisation(AuthParameters parameters) {
        if (parameters == null) {
            if (!initialInjectHappened || this.parameters != null) {
                BeforeLoginComponent component = beforeloginComponentBuilder.build();
                component.inject(this);
                this.parameters = null;
                initialInjectHappened = true;
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent
                        (REINJECT_INTENT_ACTION));
            }
        } else {
            if (!parameters.equals(this.parameters)) {
                AuthorizedComponent component = authorizedComponentBuilder.authParameters
                        (parameters).build();
                component.inject(this);
                this.parameters = parameters;
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent
                        (REINJECT_INTENT_ACTION));
            }
        }
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
