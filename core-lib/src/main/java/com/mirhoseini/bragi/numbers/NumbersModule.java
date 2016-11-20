package com.mirhoseini.bragi.numbers;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 18/11/2016.
 */

/**
 * Module which provide Numbers MVP objects
 */
@Module
class NumbersModule {

    private final NumbersView view;

    NumbersModule(NumbersView view) {
        this.view = view;
    }

    @Provides
    public NumbersView provideView() {
        return view;
    }

    @Provides
    @NumbersScope
    public NumbersInteractor provideInteractor(NumbersInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @NumbersScope
    public NumbersPresenter providePresenter(NumbersPresenterImpl presenter) {
        presenter.bind(view);
        return presenter;
    }

}
