package com.mirhoseini.bragi.base;

/**
 * Created by Mohsen on 18/11/2016.
 */

public interface BasePresenter<T> {

    void bind(T view);

    void unbind();

}
