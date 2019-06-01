package com.agiantagravic.cms.http;

import java.util.List;

public interface Paginator {
  public int getTotal();
  public int getPage();
  public List getList();
}
