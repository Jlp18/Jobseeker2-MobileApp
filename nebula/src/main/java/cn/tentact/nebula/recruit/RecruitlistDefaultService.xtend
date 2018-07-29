package cn.tentact.nebula.recruit

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

import cn.tentact.nebula.db.dao.I_ResumeDao
import cn.tentact.nebula.db.dao.I_RecruitlistDefaultDao

@Service
class RecruitlistDefaultService implements I_RecruitlistDefaultService{
	@Autowired
	I_RecruitlistDefaultDao recruitlistdefaultDao;
	
	@Autowired
	I_ResumeDao resumeDao;
	
	override searchRecruitlistDefault(String username,Integer s){
		var resumeId=resumeDao.searchMyResumeId(username);
		if(resumeId==null){			
			resumeId=-1;
		}
		var map = #{
			"resumeId" -> resumeId,
			"s" -> s
		};
		var list = recruitlistdefaultDao.searchRecruitlistDefault(map);
		return list;
	}
	
	
}