package cn.tentact.nebula.mongo.dao

import org.bson.Document
import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Criteria
import java.util.Map
import cn.tentact.nebula.mongo.pojo.Company

@Repository
class CompanyMongoDao implements I_CompanyMongoDao {
	
	@Autowired
	MongoTemplate mongo;
	
	override insert(Document document) {
		mongo.insert(document,"company");
		
	}
	
	override selectFromMongo(Integer company_id) {
		
		var query=new Query(Criteria.where("company_id").is(company_id));
		var company=mongo.findOne(query,Company);
		return company;
	}
	
	
}