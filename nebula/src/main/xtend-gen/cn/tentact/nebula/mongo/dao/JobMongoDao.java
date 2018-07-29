package cn.tentact.nebula.mongo.dao;

import cn.tentact.nebula.mongo.dao.I_JobMongoDao;
import cn.tentact.nebula.mongo.pojo.Jobdescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("all")
public class JobMongoDao implements I_JobMongoDao {
  @Autowired
  private MongoTemplate mongo;
  
  @Override
  public Jobdescription searchJobMessage(final String job_name) {
    Criteria _is = Criteria.where("jobname").is(job_name);
    Query query = new Query(_is);
    Jobdescription job = this.mongo.<Jobdescription>findOne(query, Jobdescription.class);
    return job;
  }
}
