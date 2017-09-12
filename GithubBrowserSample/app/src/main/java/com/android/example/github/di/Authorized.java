package com.android.example.github.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marks provided objects that use authorization
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Authorized {
}
