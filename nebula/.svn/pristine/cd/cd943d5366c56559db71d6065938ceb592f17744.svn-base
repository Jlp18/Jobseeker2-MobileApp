package cn.tentact.nebula.recruit

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_ResumeDao
import cn.tentact.nebula.db.dao.I_RecruitListDao

@Service
class RecruitListService implements I_RecruitListService{	
	@Autowired
	I_RecruitListDao recruitlistDao;
	
	@Autowired
	I_ResumeDao resumeDao;
	
	override searchRecruitListDefault(String username, Integer s) {
		var resumeId=resumeDao.searchMyResumeId(username);
		if(resumeId==null){			
			resumeId=-1;
		}
		var map = #{
			"resumeId" -> resumeId,
			"s" -> s
		};
		var list = recruitlistDao.searchRecruitListDefault(map);
		return list;
	}
	
	override searchRecruitListNew(String username, Integer d) {
		var resumeId=resumeDao.searchMyResumeId(username);
		if(resumeId==null){			
			resumeId=-1;
		}
		var map = #{
			"resumeId" -> resumeId,
			"d" -> d
		};
		var list = recruitlistDao.searchRecruitListNew(map);
		return list;
	}
		
	override searchRecruitListSalarymin(String username, Integer smin) {
		var resumeId=resumeDao.searchMyResumeId(username);
		if(resumeId==null){			
			resumeId=-1;
		}
		var map = #{
			"resumeId" -> resumeId,
			"smin" -> smin
		};
		var list = recruitlistDao.searchRecruitListSalarymin(map);
		return list;
	}
	
	override searchRecruitListSalarymax(String username, Integer smax) {
		var resumeId=resumeDao.searchMyResumeId(username);
		if(resumeId==null){			
			resumeId=-1;
		}
		var map = #{
			"resumeId" -> resumeId,
			"smax" -> smax
		};
		var list = recruitlistDao.searchRecruitListSalarymax(map);
		return list;
	}
	
}