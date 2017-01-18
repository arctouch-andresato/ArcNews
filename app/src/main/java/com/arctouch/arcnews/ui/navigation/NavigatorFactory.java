package com.arctouch.arcnews.ui.navigation;

import com.arctouch.arcnews.ArcNewsApplication;
import com.arctouch.arcnews.ui.FragmentFactory;

import lombok.val;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by andresato on 1/17/17.
 */

public class NavigatorFactory {

  @Inject
  FragmentFactory fragmentFactory;

  @Inject
  public NavigatorFactory() {
  }

  public Navigator with(Context context) {
    val activity = ArcNewsApplication.get(context).getCurrentActivity();
    return new Navigator(activity, fragmentFactory, activity.getSupportFragmentManager());
  }
}

