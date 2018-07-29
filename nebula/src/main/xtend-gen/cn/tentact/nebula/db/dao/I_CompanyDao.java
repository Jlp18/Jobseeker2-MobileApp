package cn.tentact.nebula.db.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_CompanyDao {
  public abstract void insert(final Map map);
  
  public abstract int searchIdByName(final String name);
  
  public abstract Map selectFromMysql(final int company_id);
}
