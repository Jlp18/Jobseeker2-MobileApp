package cn.tentact.nebula.db.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_SearchListDao {
  /**
   * 按条件搜索
   */
  public abstract List<Map> searchListDefault(final Map map);
  
  public abstract List<Map> searchListNew(final Map map);
  
  public abstract List<Map> searchListSalarymin(final Map map);
  
  public abstract List<Map> searchListSalarymax(final Map map);
}
