package com.arctouch.arcnews.ui.navigation;

import com.arctouch.arcnews.R;
import com.arctouch.arcnews.ui.FragmentFactory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.val;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class Navigator {

    private final Context context;
    private final FragmentFactory factory;
    private final FragmentManager manager;

    public void navigateToInitialScreen() {
        navigateTo(factory.PostListFragment(context), false);
    }

    public void navigateToShoppingList() {
        navigateTo(factory.PostListFragment(context));
    }

    private void navigateTo(Fragment fragment) {
        navigateTo(fragment, true);
    }

    private void navigateTo(Fragment fragment, boolean keepInBackStack) {
        val transaction = manager.beginTransaction()
                .replace(R.id.fragment_container, fragment);

        if (keepInBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
