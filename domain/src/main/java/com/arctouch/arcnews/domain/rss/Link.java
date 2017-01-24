package com.arctouch.arcnews.domain.rss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by andresato on 1/23/17.
 */
@Data
@AllArgsConstructor
@Root(name = "link", strict=false)
public class Link {
  @Getter
  @Attribute
  String href;

  public Link() { }
}
