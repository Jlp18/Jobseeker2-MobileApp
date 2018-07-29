package cn.tentact.nebula.company

import java.util.Map
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_CompanyDao
import cn.tentact.nebula.mongo.dao.I_CompanyMongoDao
import org.springframework.transaction.annotation.Transactional
import org.bson.Document

@Service
class CompanyService implements I_CompanyService {
	@Autowired
	I_CompanyMongoDao companyMongoDao;
	@Autowired
	I_CompanyDao companyDao;
	
	@Transactional
	override insert(Map map) {
		var one=#{
			"name"->map.get("name"),
			"tel"->map.get("tel"),
			"website"->map.get("website"),
			"scale"->map.get("scale"),
			"type"->map.get("type")
		};
		companyDao.insert(one);  //写入MySQL
		var id=companyDao.searchIdByName(map.get("name") as String);
		
		var document=new Document();
		document.put("company_id",id);
		document.put("resume",map.get("resume") as String);
		
		companyMongoDao.insert(document);
	}
	
	override selectFromMongo(Integer company_id) {
		return companyMongoDao.selectFromMongo(company_id);
	}
	
	override selectFromMysql(int company_id) {
		var map=companyDao.selectFromMysql(company_id);
		return map;
		
	}
	
	
}