package com.mirhoseini.bragi.numbers;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 18/11/2016.
 */

@Module
class NumbersModule {

    private NumbersView view;

    NumbersModule(NumbersView view) {
        this.view = view;
    }

    @Provides
    public NumbersView provideView() {
        return view;
    }

    @Provides
    @Numbers
    public NumbersInteractor provideInteractor(NumbersInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @Numbers
    public NumbersPresenter providePresenter(NumbersPresenterImpl presenter) {
        presenter.bind(view);
        return presenter;
    }

}
