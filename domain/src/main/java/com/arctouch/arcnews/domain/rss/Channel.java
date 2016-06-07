package com.arctouch.arcnews.domain.rss;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Channel {
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private List<Item> items;
}
