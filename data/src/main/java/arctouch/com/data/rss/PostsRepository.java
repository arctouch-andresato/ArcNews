package arctouch.com.data.rss;

import com.arctouch.arcnews.domain.rss.Item;
import com.arctouch.arcnews.domain.rss.Rss;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import arctouch.com.data.infra.ObservableUtil;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;

@Singleton
//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PostsRepository implements com.arctouch.arcnews.domain.rss.PostsRepository{

    private final ObservableUtil observables;

    private List<Item> postListCache = new ArrayList<>();

    @Inject
    public PostsRepository(ObservableUtil observables) {
        this.observables = observables;
    }

    @Override
    public Observable<List<Item>> getPostList() {
        return Observable.just(postListCache)
                .concatWith(refresh())
                .compose(observables.defaultSchedulers());
    }

    private Observable<List<Item>> refresh() {
        return Observable.<List<Item>>create(subscriber -> {
            List<Item> posts = retrievePostList();

            postListCache = posts;
            subscriber.onNext(posts);
            subscriber.onCompleted();
        });
    }

    private List<Item> retrievePostList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.blog.wordpress.com/feed/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        Rss rss = retrofit.create(Rss.class);

        return rss.getChannel().getItems();
    }
}
