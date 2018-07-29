package cn.tentact.nebula.mongo.dao

import org.bson.Document
import cn.tentact.nebula.mongo.pojo.Company

interface I_CompanyMongoDao {
	
	def void insert(Document document);
	def Company selectFromMongo(Integer company_id);
}