package cn.tentact.nebula.mongo.dao

import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Criteria

import cn.tentact.nebula.mongo.pojo.Jobdescription

@Repository
class JobMongoDao implements I_JobMongoDao  {
	
	@Autowired
	MongoTemplate mongo;
	
	override searchJobMessage(String job_name) {
		var query=new Query(Criteria.where("jobname").is(job_name));
		var job=mongo.findOne(query,Jobdescription);
		return job;
	}
	
}