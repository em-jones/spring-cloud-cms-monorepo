package com.agiantagravic.cms.http;

import com.agiantagravic.cms.data.Content;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentResponseFactory {

  public ContentPaginator paginator(Page<Content> page){
    return new ContentPaginator(page);
  }

  class ContentPaginator implements Paginator{

    private int page;
    private int total;
    private int totalPages;
    private List<Content> list;

    ContentPaginator(Page<Content> page){
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

    public List<Content> getList() {
      return list;
    }

    public void setList(List<Content> list) {
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
