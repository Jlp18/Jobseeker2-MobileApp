package cn.tentact.nebula.mongo.dao;

import cn.tentact.nebula.mongo.pojo.Company;
import org.bson.Document;

@SuppressWarnings("all")
public interface I_CompanyMongoDao {
  public abstract void insert(final Document document);
  
  public abstract Company selectFromMongo(final Integer company_id);
}
