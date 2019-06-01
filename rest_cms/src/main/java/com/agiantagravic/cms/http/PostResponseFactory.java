package com.agiantagravic.cms.http;

import com.agiantagravic.cms.data.Post;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostResponseFactory {

  public PostPaginator paginator(Page<Post> page){
    return new PostPaginator(page);
  }

  class PostPaginator implements Paginator{

    private int page;
    private int total;
    private int totalPages;
    private List<Post> list;

    PostPaginator(Page<Post> page){
      list = page.getContent();
      totalPages = page.getTotalPages();
      total = (int) page.getTotalElements();
    }

    public int getPage() {
      return page;
    }

    public void setPage(int page) {
      this.page = page;
    }

    public int getTotal() {
      return total;
    }

    public void setTotal(int total) {
      this.total = total;
    }

    public List<Post> getList() {
      return list;
    }

    public void setList(List<Post> list) {
      this.list = list;
    }

    public int getTotalPages() {
      return totalPages;
    }

    public void setTotalPages(int totalPages) {
      this.totalPages = totalPages;
    }
  }
}
