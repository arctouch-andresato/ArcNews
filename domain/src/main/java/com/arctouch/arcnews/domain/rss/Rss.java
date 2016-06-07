package com.arctouch.arcnews.domain.rss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Rss {
    @Getter
    private Channel channel;
}
