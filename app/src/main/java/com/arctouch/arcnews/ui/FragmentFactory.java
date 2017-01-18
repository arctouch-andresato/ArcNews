package com.arctouch.arcnews.ui;

import com.arctouch.arcnews.ArcNewsApplication;

import lombok.val;

import android.content.Context;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

/**
 * A Factory for creating fragments (duh).
 * Seriously, it will return you fragments "ready for use" - in other words, with their dependencies injected.
 */
public class FragmentFactory {

    @Inject
    public FragmentFactory() {

    }

    public Fragment PostListFragment(Context context) {
        val fragment = new PostListFragment();
        ArcNewsApplication.get(context).getApplicationComponent().inject(fragment);
        return fragment;
    }

}
