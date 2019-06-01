package com.agiantagravic.cms.http;

import com.agiantagravic.cms.data.Post;
import com.agiantagravic.cms.data.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PostRequest {
  private Set<Tag> tags = new HashSet<>();
  private Post post = new Post();

  public Set<Tag> getTags() {
    return tags;
  }

  public void setTags(Set<Tag> tags) {
    this.tags = tags;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}
