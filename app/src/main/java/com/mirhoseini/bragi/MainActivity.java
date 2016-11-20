package com.mirhoseini.bragi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.mirhoseini.bragi.base.BaseActivity;
import com.mirhoseini.bragi.base.BaseView;
import com.mirhoseini.bragi.numbers.NumbersFragment;
import com.mirhoseini.bragi.numbers.adapter.model.Number;
import com.mirhoseini.utils.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class MainActivity extends BaseActivity implements BaseView, NumbersFragment.OnListFragmentInteractionListener {

    public static final String TAG_NUMBERS_FRAGMENT = "numbers_fragment";

    // injecting dependencies via Dagger
    @Inject
    Context context;
    @Inject
    Resources resources;

    // injecting views via ButterKnife
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private NumbersFragment numbersFragment;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inject views using ButterKnife
        ButterKnife.bind(this);

        setupToolbar();

        if (null == savedInstanceState) {
            numbersFragment = NumbersFragment.newInstance();
            attachFragments();
        } else {
            numbersFragment = (NumbersFragment) getSupportFragmentManager().findFragmentByTag(TAG_NUMBERS_FRAGMENT);
        }

    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.logo);
        getSupportActionBar().setTitle(R.string.main_title);
    }

    private void attachFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.numbers_fragment, numbersFragment, TAG_NUMBERS_FRAGMENT);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showOfflineMessage(boolean isForce) {
        if (isForce)
            Utils.showNoInternetConnectionDialog(this, true);
        else
            Snackbar.make(toolbar, R.string.offline_message, Snackbar.LENGTH_LONG)
                    .setAction(R.string.go_online, v -> startActivity(new Intent(
                            Settings.ACTION_WIFI_SETTINGS)))
                    .setActionTextColor(Color.GREEN)
                    .show();
    }

    @Override
    public void showRetryMessage(Throwable throwable) {
        Snackbar.make(toolbar, resources.getString(R.string.retry_message), Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, view -> numbersFragment.loadData())
                .show();
    }

    @Override
    public void showProgress() {
        if (null != progressDialog)
            progressDialog.dismiss();

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle(R.string.please_wait);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (null != progressDialog)
            progressDialog.dismiss();
    }

    @Override
    public void showData(Number number) {
        // this event can cause opening data details in new activity
        showMessage(number.toString());
    }
}
