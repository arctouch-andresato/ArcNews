package com.arctouch.arcnews.domain.rss;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Root(name = "rss", strict=false)
public class Rss {
    @Element(name="channel")
    @Getter
    private Channel channel;


    public Rss() {
    }
}
