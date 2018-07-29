package cn.tentact.nebula.db.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_UserLevelDao {
  public abstract int searchExp(final String username);
  
  public abstract int updateExp(final Map map);
}
