package cn.tentact.nebula.resume

import org.springframework.stereotype.Service
import java.util.Map
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_ResumeDao
import cn.tentact.nebula.db.dao.I_RecruitResumeDao
import org.springframework.transaction.annotation.Transactional
import cn.tentact.nebula.shiro.JwtUtil
import cn.tentact.nebula.db.dao.I_UserDao

@Service
class ResumeService implements I_ResumeService {
	@Autowired
	I_UserDao userDao;
	
	@Autowired
	I_ResumeDao resumeDao;
	
	@Autowired
	I_RecruitResumeDao recruitResumeDao;
	
	@Transactional
	override sendMyResume(Map map) {
		var username=map.get("username") as String;
		var resumeId=resumeDao.searchMyResumeId(username);  //查询我的简历id
		if(resumeId==null){
			return -1;
		}
		map.put("resumeId",resumeId);
		var i=recruitResumeDao.add(map);
		return i;
	}
	
	override updateResumeinfo(Map map) {
		resumeDao.updateResumeinfo(map);
	}
	
	override insertResumeinfo(Map map) {
		resumeDao.insertResumeinfo(map);
	}
	
	override selectResumeinfo(int user_id) {
		var map=resumeDao.selectResumeinfo(user_id);
		return map;
	}
	
	override updatePhoto(Map map) {
		var photo_path=map.get("photo_path") as String;
		var username=map.get("username") as String;
		var user_id=userDao.searchId(username);
		var one=#{
			"photo_path"->photo_path,
			"username"->username
		};
		userDao.updatePhoto(one);
		var two=#{
			"photo_path"->photo_path,
			"user_id"->user_id
		};
		resumeDao.updatePhoto(two);
	}
	
}