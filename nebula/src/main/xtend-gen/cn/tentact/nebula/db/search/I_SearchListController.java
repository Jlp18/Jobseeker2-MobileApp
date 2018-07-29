package cn.tentact.nebula.db.search;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_SearchListController {
  /**
   * 按条件搜索
   */
  public abstract ResponseBean searchListDefault(final String token, final String content);
  
  public abstract ResponseBean searchListNew(final String token, final String content);
  
  public abstract ResponseBean searchListSalarymin(final String token, final String content);
  
  public abstract ResponseBean searchListSalarymax(final String token, final String content);
}
