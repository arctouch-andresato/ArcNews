package com.arctouch.arcnews.ui;

import com.arctouch.arcnews.R;
import com.arctouch.arcnews.data.infra.ui.ObservableAdapter;
import com.arctouch.arcnews.domain.rss.Item;
import com.arctouch.arcnews.presenter.NewsListPresenter;
import com.arctouch.arcnews.view.NewsListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.val;
import rx.Observable;
import rx.functions.Action0;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by andresato on 1/17/17.
 */

public class NewsFragment extends Fragment implements NewsListView {

  private static final String TAG = NewsFragment.class.getSimpleName();

  private ObservableAdapter<Item> listAdapter;
  private Unbinder unbinder;

  @BindView(R.id.list)
  ListView newsView;

  @BindView(R.id.refresh_layout)
  SwipeRefreshLayout refreshLayout;

  @Inject
  NewsListPresenter presenter;

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    Log.d(TAG, "Creating view...");
    val mainView = inflater.inflate(R.layout.news_list_fragment, container, false);
    this.unbinder = ButterKnife.bind(this, mainView);

    this.listAdapter = new ObservableAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1);
    this.newsView.setAdapter(listAdapter);

    this.presenter.setupView(this);

    return mainView;
  }

  @Override
  public void showRefreshProgress() {
    Log.d(TAG, "Setting refreshing to true...");
    this.refreshLayout.post(() ->
        this.refreshLayout.setRefreshing(true)
    );
  }

  @Override
  public void hideRefreshProgress() {
    Log.d(TAG, "Setting refreshing to false...");
    this.refreshLayout.post(() ->
        this.refreshLayout.setRefreshing(false)
    );
  }

  @Override
  public void setOnRefreshListener(Action0 listener) {
    this.refreshLayout.setOnRefreshListener(listener::call);
  }

  @Override
  public void showData(Observable<List<Item>> posts) {
    this.listAdapter.setObservable(posts);
  }

  @Override
  public void onDestroyView() {
    Log.d(TAG, "Destroying view...");
    super.onDestroyView();
//    this.listAdapter.close();
    unbinder.unbind();
  }
}
