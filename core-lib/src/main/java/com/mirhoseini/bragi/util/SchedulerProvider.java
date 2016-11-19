package com.mirhoseini.bragi.util;

import rx.Scheduler;

/**
 * Created by Mohsen on 18/11/2016.
 */

public interface SchedulerProvider {

    Scheduler mainThread();

    Scheduler backgroundThread();

}
