package cn.tentact.nebula.db.search;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface I_SearchListService {
  /**
   * 按条件搜索
   */
  public abstract List<Map> searchListDefault(final String username, final String content);
  
  public abstract List<Map> searchListNew(final String username, final String content);
  
  public abstract List<Map> searchListSalarymin(final String username, final String content);
  
  public abstract List<Map> searchListSalarymax(final String username, final String content);
}
