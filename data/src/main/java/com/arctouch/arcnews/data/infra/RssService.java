package com.arctouch.arcnews.data.infra;

import com.arctouch.arcnews.domain.rss.Rss;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RssService {
    //@GET("feed/")
    //@GET("reutersmedia/")

    @GET("wiki/createrssfeed.action?pageSubTypes=comment&pageSubTypes=attachment&types=blogpost&spaces=~andre.sato&title=Confluence+RSS+Feed&labelString%3D&excludedSpaceKeys%3D&sort=modified&maxResults=10&timeSpan=5&showContent=true&confirm=Create+RSS+Feed&os_authType=basic")
    Call<Rss> getRss(@Header("Authorization") String authorization);
}
