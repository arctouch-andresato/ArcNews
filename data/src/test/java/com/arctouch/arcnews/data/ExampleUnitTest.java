package com.arctouch.arcnews.data;

import com.arctouch.arcnews.data.infra.InfraModule;
import com.arctouch.arcnews.data.infra.ObservableUtil;
import com.arctouch.arcnews.data.rss.PostRepository;
import com.arctouch.arcnews.domain.rss.Item;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.plugins.RxJavaTestPlugins;
import rx.schedulers.Schedulers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    private List<Item> posts;

  @Before
  public void setup() {
    setupRxSchedulers();
  }

  private void setupRxSchedulers() {
    RxJavaTestPlugins.resetPlugins();
    RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook() {
      @Override
      public Scheduler getIOScheduler() {
        return Schedulers.immediate();
      }
    });
    RxAndroidPlugins.getInstance().reset();
    RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
      @Override
      public Scheduler getMainThreadScheduler() {
        return Schedulers.immediate();
      }
    });
  }

    @Test
    public void retrievingPosts_isCorrect() throws Exception {
        posts = new ArrayList<>();
        PostRepository repo = new PostRepository(new ObservableUtil(new InfraModule().providesSchedulingTransformer()));
        repo.getPostList().forEach(items-> {
                    for (Item item: items
                    ){
                        System.out.println(item.getTitle());
                        System.out.println(item.getDescription());
                        System.out.println(item.getLink());
                    }
                }
        );
    }
}