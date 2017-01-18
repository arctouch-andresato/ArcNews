package com.arctouch.arcnews.presenter;

import com.arctouch.arcnews.data.rss.PostsRepository;
import com.arctouch.arcnews.ui.navigation.Navigator;
import com.arctouch.arcnews.ui.navigation.NavigatorFactory;
import com.arctouch.arcnews.view.NewsListView;

import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by andresato on 1/17/17.
 */

public class NewsListPresenter {

  private final PostsRepository posts;
  private Navigator navigator;
  private final NavigatorFactory navigatorFactory;

  @Inject
  public NewsListPresenter (PostsRepository posts, NavigatorFactory navigatorFactory) {
    this.posts = posts;
    this.navigatorFactory = navigatorFactory;
  }

  public void setupView(@NonNull NewsListView view) {
    this.navigator = navigatorFactory.with(view.getContext());
    refreshNewsList(view);
    setupAddGroceryItemListener(view);
    setupRefreshListener(view);
  }

  private void refreshNewsList(NewsListView view) {
    view.showData(posts.getPostList()
        .doOnSubscribe(view::showRefreshProgress)
        .doOnCompleted(view::hideRefreshProgress));
  }

  public void setupAddGroceryItemListener(NewsListView view) {
//    view.setOnAddItemListener(() -> {
//      //navigator.navigateToAddGroceryItemScreen();
//    });
  }

  private void setupRefreshListener(NewsListView view) {
    view.setOnRefreshListener(() -> {
      refreshNewsList(view);
    });
  }
}
