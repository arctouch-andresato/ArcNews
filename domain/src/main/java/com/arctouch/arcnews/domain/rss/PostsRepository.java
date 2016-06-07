package com.arctouch.arcnews.domain.rss;

import java.util.List;

import rx.Observable;

public interface PostsRepository {

    Observable<List<Item>> getPostList();
}
