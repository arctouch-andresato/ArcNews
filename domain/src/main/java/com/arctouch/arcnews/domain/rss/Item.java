package com.arctouch.arcnews.domain.rss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@AllArgsConstructor
@Root(name = "entry", strict=false)
public class Item {
    @Getter
    @Element(name = "title", required = true)
    private String title;//The title of the item.	Venice Film Festival Tries to Quit Sinking
    @Getter
    @Element(name = "link", required = true)
    private Link link;//The URL of the item.	http://www.nytimes.com/2002/09/07/movies/07FEST.html
    @Getter
    @Element(name = "summary", required = true)
    private String description;//The item synopsis.	Some of the most heated chatter at the Venice Film Festival this week was about the way that the arrival of the stars at the Palazzo del Cinema was being staged

    public Item() {
    }
}
