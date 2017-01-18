package com.arctouch.arcnews;

import com.arctouch.arcnews.data.infra.ui.ActivityLifecycleCallbacksBuilder;
import com.arctouch.arcnews.infra.ApplicationComponent;
import com.arctouch.arcnews.infra.DaggerApplicationComponent;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import io.fabric.sdk.android.Fabric;
import lombok.Getter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by andresato on 6/13/16.
 */
public class ArcNewsApplication extends Application {

    private static final String TAG = ArcNewsApplication.class.getSimpleName();

    public static ArcNewsApplication get(Context context) {
        return (ArcNewsApplication) context.getApplicationContext();
    }

    @Getter
    private ApplicationComponent applicationComponent;

    @Getter
    private AppCompatActivity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Answers(), new Crashlytics());

        this.applicationComponent = DaggerApplicationComponent.create();
        super.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksBuilder()
                .onActivityCreated(this::onActivityCreated)
                .onActivityDestroyed(this::onActivityDestroyed)
                .build());
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(TAG, "Setting currentActivity as " + activity);
        if (activity instanceof AppCompatActivity) {
            this.currentActivity = (AppCompatActivity) activity;
        }
    }

    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG, "Clearing currentActivity");
        if (currentActivity == activity) {
            currentActivity = null;
        }
    }
}
