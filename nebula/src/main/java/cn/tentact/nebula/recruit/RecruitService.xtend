package cn.tentact.nebula.recruit

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_RecruitDao
import cn.tentact.nebula.db.dao.I_ResumeDao

@Service
class RecruitService implements I_RecruitService {
	@Autowired
	I_RecruitDao recruitDao;
	
	@Autowired
	I_ResumeDao resumeDao;
	
	override searchCurrentRecruit(String username) {
		var resumeId=resumeDao.searchMyResumeId(username);
		if(resumeId==null){
			resumeId=-1;
		}
		var list = recruitDao.searchCurrentRecruit(resumeId);
		return list;
	}
	
}