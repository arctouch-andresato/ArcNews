package com.arctouch.arcnews.data.infra.ui;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import lombok.val;
import rx.functions.Action1;
import rx.functions.Action2;

public class ActivityLifecycleCallbacksBuilder {

    private Action2<Activity, Bundle> activityCreated;
    private Action1<Activity> activityDestroyed;

    public ActivityLifecycleCallbacksBuilder onActivityCreated(Action2<Activity, Bundle> activityCreated) {
        this.activityCreated = activityCreated;
        return this;
    }

    public ActivityLifecycleCallbacksBuilder onActivityDestroyed(Action1<Activity> activityDestroyed) {
        this.activityDestroyed = activityDestroyed;
        return this;
    }

    public Application.ActivityLifecycleCallbacks build() {
        val activityCreated = this.activityCreated;
        val activityDestroyed = this.activityDestroyed;

        return new Application.ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (activityCreated != null) {
                    activityCreated.call(activity, savedInstanceState);
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (activityDestroyed != null) {
                    activityDestroyed.call(activity);
                }
            }
        };
    }

}
