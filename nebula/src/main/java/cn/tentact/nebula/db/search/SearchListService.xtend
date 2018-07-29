package cn.tentact.nebula.db.search

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_ResumeDao
import cn.tentact.nebula.db.dao.I_SearchListDao

@Service
class SearchListService implements I_SearchListService{
	
	@Autowired
	I_SearchListDao searchlistDao;
	
	@Autowired
	I_ResumeDao resumeDao;
	
	override searchListDefault(String username, String content) {
		var resumeId=resumeDao.searchMyResumeId(username);
		if(resumeId==null){			
			resumeId=-1;
		}
		var map = #{
			"resumeId" -> resumeId,
			"content" -> content
		};
		var list = searchlistDao.searchListDefault(map);
		return list;
	}
	
	override searchListNew(String username, String content) {
		var resumeId=resumeDao.searchMyResumeId(username);
		if(resumeId==null){			
			resumeId=-1;
		}
		var map = #{
			"resumeId" -> resumeId,
			"content" -> content
		};
		var list = searchlistDao.searchListNew(map);
		return list;
	}
	
	override searchListSalarymin(String username, String content) {
		var resumeId=resumeDao.searchMyResumeId(username);
		if(resumeId==null){			
			resumeId=-1;
		}
		var map = #{
			"resumeId" -> resumeId,
			"content" -> content
		};
		var list = searchlistDao.searchListSalarymin(map);
		return list;
	}
	
	override searchListSalarymax(String username, String content) {
		var resumeId=resumeDao.searchMyResumeId(username);
		if(resumeId==null){			
			resumeId=-1;
		}
		var map = #{
			"resumeId" -> resumeId,
			"content" -> content
		};
		var list = searchlistDao.searchListSalarymax(map);
		return list;
	}
	
}