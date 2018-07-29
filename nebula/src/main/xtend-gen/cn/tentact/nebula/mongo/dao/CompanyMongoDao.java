package cn.tentact.nebula.mongo.dao;

import cn.tentact.nebula.mongo.dao.I_CompanyMongoDao;
import cn.tentact.nebula.mongo.pojo.Company;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("all")
public class CompanyMongoDao implements I_CompanyMongoDao {
  @Autowired
  private MongoTemplate mongo;
  
  @Override
  public void insert(final Document document) {
    this.mongo.insert(document, "company");
  }
  
  @Override
  public Company selectFromMongo(final Integer company_id) {
    Criteria _is = Criteria.where("company_id").is(company_id);
    Query query = new Query(_is);
    Company company = this.mongo.<Company>findOne(query, Company.class);
    return company;
  }
}
