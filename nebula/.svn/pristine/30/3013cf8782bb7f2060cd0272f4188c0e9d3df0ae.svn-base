package cn.tentact.nebula.recruit

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_ResumeDao
import cn.tentact.nebula.db.dao.I_RecruitlistNewDao

@Service
class RecruitlistNewService implements I_RecruitlistNewService{
	
	@Autowired
	I_RecruitlistNewDao recruitlistnewDao;
	
	@Autowired
	I_ResumeDao resumeDao;
	
	override searchRecruitlistNew(String username, Integer d) {
		var resumeId=resumeDao.searchMyResumeId(username);
		if(resumeId==null){			
			resumeId=-1;
		}
		var map = #{
			"resumeId" -> resumeId,
			"d" -> d
		};
		var list = recruitlistnewDao.searchRecruitlistNew(map);
		return list;
	}
	
}