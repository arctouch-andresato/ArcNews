package com.arctouch.arcnews.data.infra;

import com.arctouch.arcnews.domain.rss.Rss;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RssService {
    @GET("feed/")
    //@GET("reutersmedia/")
    Call<Rss> getRss();
}
