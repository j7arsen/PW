package com.j7arsen.pw.di.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by arsen on 26.02.2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthScope {
}
