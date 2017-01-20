package com.arctouch.arcnews.infra;

import com.arctouch.arcnews.data.infra.DataModule;
import com.arctouch.arcnews.data.infra.InfraModule;
import com.arctouch.arcnews.ui.MainActivity;
import com.arctouch.arcnews.ui.PostListFragment;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DataModule.class, InfraModule.class  })
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);


    void inject(PostListFragment postListFragment);
}
