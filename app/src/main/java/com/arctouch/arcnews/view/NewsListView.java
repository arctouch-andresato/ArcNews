package com.arctouch.arcnews.view;

import com.arctouch.arcnews.domain.rss.Item;
import com.arctouch.arcnews.infra.Contextualized;

import rx.Observable;
import rx.functions.Action0;

import java.util.List;

/**
 * Created by andresato on 6/14/16.
 */
public interface NewsListView extends Contextualized {
    void showData(Observable<List<Item>> groceries);

    void showRefreshProgress();
    void hideRefreshProgress();
    //void setOnAddItemListener(Action0 listener);
    void setOnRefreshListener(Action0 listener);
}
