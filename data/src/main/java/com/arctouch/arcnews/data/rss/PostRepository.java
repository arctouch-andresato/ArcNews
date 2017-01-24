package com.arctouch.arcnews.data.rss;

import com.arctouch.arcnews.data.infra.ObservableUtil;
import com.arctouch.arcnews.data.infra.RssService;
import com.arctouch.arcnews.domain.rss.Item;

import lombok.RequiredArgsConstructor;
import lombok.val;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import rx.Observable;

import android.util.Base64;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PostRepository implements com.arctouch.arcnews.domain.rss.PostsRepository{

    private final ObservableUtil observables;

    private List<Item> postListCache = new ArrayList<>();


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

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        HttpUrl url = new HttpUrl.Builder().scheme("https").host("arctouch.atlassian.net")
//            .addPathSegment("wiki/createrssfeed.action")
//            .addQueryParameter("types","page")
//            .addQueryParameter("types","blogpost")
//            .addQueryParameter("spaces","conf_all")
//            .addQueryParameter("title","Confluence+RSS+Feed")
//            .addQueryParameter("labelString","excludedSpaceKeys")
//            .addQueryParameter("sort","modified")
//            .addQueryParameter("maxResults","10")
//            .addQueryParameter("timeSpan","5")
//            .addQueryParameter("showContent","true")
//            .addQueryParameter("confirm","Create+RSS+Feed")
//            .addQueryParameter("os_authType","basic").build();

//        HttpUrl url = HttpUrl.parse("https://arctouch.atlassian.net/wiki/createrssfeed" +
//            ".action?types=page&types=blogpost&spaces=conf_all&title=Confluence+RSS+Feed" +
//            "&labelString%3D&excludedSpaceKeys%3D&sort=modified&maxResults=10&timeSpan=5&showContent=true&confirm=Create+RSS+Feed&os_authType=basic/");

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://arctouch.atlassian.net/")
        //https://arctouch.atlassian.net/wiki/createrssfeed
        // types=page&types=blogpost&spaces=conf_all&title=Confluence+RSS+Feed&labelString=&excludedSpaceKeys=&sort=modified&maxResults=10&timeSpan=5&showContent=true&confirm=Create+RSS+Feed
        // types=page&types=blogpost&spaces=conf_all&title=Confluence+RSS+Feed&labelString%3D&excludedSpaceKeys%3D&sort=modified&maxResults=10&timeSpan=5&showContent=true&confirm=Create+RSS+Feed&os_authType=basic
                //.baseUrl("https://en.blog.wordpress.com/")
                //.client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        String basicAuth = "Basic " + Base64.encodeToString(String.format("%s:%s", "andre.sato",
            "arctouch-11").getBytes(), Base64.NO_WRAP);
        RssService rssService = retrofit.create(RssService.class);
        val response = rssService.getRss(basicAuth).execute();

        return response.body().getItems();
    }
}
