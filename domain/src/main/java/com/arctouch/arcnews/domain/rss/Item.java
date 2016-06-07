package com.arctouch.arcnews.domain.rss;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private String title;
    private String description;
    private String link;
}
