package arctouch.com.data.rss;

import com.arctouch.arcnews.domain.rss.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import arctouch.com.data.infra.ObservableUtil;
import arctouch.com.data.infra.RssService;
import lombok.val;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
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
            List<Item> posts = null;
            try {
                posts = retrievePostList();
            } catch (IOException e) {
                e.printStackTrace();
            }

            postListCache = posts;
            subscriber.onNext(posts);
            subscriber.onCompleted();
        });
    }

    private List<Item> retrievePostList() throws IOException{

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.blog.wordpress.com/")
                //.baseUrl("http://feeds.reuters.com/news/")
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        RssService rssService = retrofit.create(RssService.class);

        val response = rssService.getRss().execute();
        return response.body().getChannel().getItems();
    }
}
