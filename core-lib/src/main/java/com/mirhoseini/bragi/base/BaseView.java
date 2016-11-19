package com.mirhoseini.bragi.base;

/**
 * Created by Mohsen on 18/11/2016.
 */

public interface BaseView {

    void showOfflineMessage(boolean isForce);

    void showRetryMessage(Throwable throwable);

    void showMessage(String message);

    void showError(Throwable throwable);

    void showProgress();

    void hideProgress();

}
