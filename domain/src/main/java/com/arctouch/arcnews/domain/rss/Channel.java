package com.arctouch.arcnews.domain.rss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Data
@AllArgsConstructor
@Root(name = "feed", strict=false)
public class Channel {
    @Getter
    @Element(name="title")
    private String title;
    @Getter
    @Element(name="subtitle")
    private String description;
    @Getter
    @ElementList(inline = true, name="entry")
    private List<Item> items;

    public Channel() {
    }
}
