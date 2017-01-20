package com.arctouch.arcnews.presenter;

import com.arctouch.arcnews.data.rss.PostRepository;
import com.arctouch.arcnews.domain.rss.PostsRepository;
import com.arctouch.arcnews.ui.navigation.Navigator;
import com.arctouch.arcnews.ui.navigation.NavigatorFactory;
import com.arctouch.arcnews.view.PostsListView;

import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by andresato on 1/17/17.
 */

public class PostListPresenter {

  private final PostsRepository posts;
  private Navigator navigator;
  private final NavigatorFactory navigatorFactory;

  @Inject
  public PostListPresenter(PostRepository posts, NavigatorFactory navigatorFactory) {
    this.posts = posts;
    this.navigatorFactory = navigatorFactory;
  }

  public void setupView(@NonNull PostsListView view) {
    this.navigator = navigatorFactory.with(view.getContext());
    refreshNewsList(view);
    setupAddGroceryItemListener(view);
    setupRefreshListener(view);
  }

  private void refreshNewsList(PostsListView view) {
    view.showData(posts.getPostList()
        .doOnSubscribe(view::showRefreshProgress)
        .doOnCompleted(view::hideRefreshProgress));
  }

  public void setupAddGroceryItemListener(PostsListView view) {
//    view.setOnAddItemListener(() -> {
//      //navigator.navigateToAddGroceryItemScreen();
//    });
  }

  private void setupRefreshListener(PostsListView view) {
    view.setOnRefreshListener(() -> {
      refreshNewsList(view);
    });
  }
}
