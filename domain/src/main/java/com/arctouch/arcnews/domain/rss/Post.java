package com.arctouch.arcnews.domain.rss;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
    private int id;
    private int title;
    private int text;
}
