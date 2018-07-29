package cn.tentact.nebula.user

import java.util.Map
import cn.tentact.nebula.db.dao.I_UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import cn.tentact.nebula.db.dao.I_UserLevelDao
import cn.tentact.nebula.db.dao.I_RecruitResumeDao

/**
 * 用户模块实现
 */
 @Service
class UserService implements I_UserService {
	@Autowired   //注入对象
	I_UserDao userDao;
	@Autowired
	I_UserLevelDao userLevelDao;
	@Autowired
	I_RecruitResumeDao recruitResumeDao;
	
	override login(Map map) {
		var count=userDao.login(map);
		if(count == 1){
			return true;
		}else{
			return false;
		}
	}
	
	override searchSummary(String username) {
		var photoPath=userDao.searchPhoto(username);
		var exp = userLevelDao.searchExp(username);  //查询用户经验值
		var resumeCount = recruitResumeDao.searchMyResumeCount(username);
		return #{"exp" -> exp, "resumeCount" -> resumeCount,"photoPath"->photoPath};
	}
	
	override register(Map map) {
		var username=map.get("username") as String;
		var j=userDao.existIf(username);
		if(j==1){
			return 0;
		}
		else{
			var i=userDao.addUser(map);
			userDao.addUserLevel();
			return i;
		}
		
	}
		
	override updatePassword(Map map) {
		var i=userDao.insertNewPassword(map);
		return i;
	}
	
}