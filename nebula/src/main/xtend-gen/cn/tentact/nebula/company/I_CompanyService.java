package cn.tentact.nebula.company;

import cn.tentact.nebula.mongo.pojo.Company;
import java.util.Map;

@SuppressWarnings("all")
public interface I_CompanyService {
  public abstract void insert(final Map map);
  
  public abstract Company selectFromMongo(final Integer company_id);
  
  public abstract Map selectFromMysql(final int company_id);
}
