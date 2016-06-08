package com.arctouch.arcnews.domain.rss;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Root(name = "channel", strict=false)
public class Channel {
    @Getter
    @Element(name="title")
    private String title;
    @Getter
    @Element(name="description")
    private String description;
    @Getter
    @ElementList(inline = true, name="item")
    private List<Item> items;

    public Channel() {
    }
}
