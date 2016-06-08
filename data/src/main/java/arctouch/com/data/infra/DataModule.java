package arctouch.com.data.infra;

import arctouch.com.data.rss.PostsRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    @Provides
    public PostsRepository provideGroceryRepository(PostsRepository postsRepository) {
        return postsRepository;
    }
}
