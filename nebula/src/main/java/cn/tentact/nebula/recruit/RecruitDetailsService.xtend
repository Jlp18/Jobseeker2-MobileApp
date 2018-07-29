package cn.tentact.nebula.recruit

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_RecruitDetailsDao
import cn.tentact.nebula.mongo.dao.I_JobMongoDao

@Service
class RecruitDetailsService implements I_RecruitDetailsService {
	@Autowired
	I_RecruitDetailsDao recruitDetailsDao;
	@Autowired
	I_JobMongoDao jobMongoDao;
	
	override RecruitDetails(int recruit_id) {
		var map=recruitDetailsDao.recruitDetails(recruit_id);
		return map;
	}
	
	override searchJobMessage(int recruit_id) {
		var job_name=recruitDetailsDao.searchJobnameByRecruitId(recruit_id);
		return jobMongoDao.searchJobMessage(job_name);
		
	}
	
}