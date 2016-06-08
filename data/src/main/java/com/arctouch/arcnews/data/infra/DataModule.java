package com.arctouch.arcnews.data.infra;

import com.arctouch.arcnews.data.rss.PostsRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    @Provides
    public PostsRepository provideGroceryRepository(PostsRepository postsRepository) {
        return postsRepository;
    }
}
