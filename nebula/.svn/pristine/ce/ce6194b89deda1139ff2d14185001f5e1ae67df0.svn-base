package cn.tentact.nebula.user

import org.springframework.stereotype.Service
import java.util.Map
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_UserDao
import cn.tentact.nebula.db.dao.I_UserLevelDao
import org.springframework.transaction.annotation.Transactional

@Service
class UserLevelService implements I_UserLevelService {
	@Autowired
	I_UserDao userDao;
	@Autowired
	I_UserLevelDao userLevelDao;
	
	@Transactional
	override updateExp(Map map) {
		var username=map.get("username") as String;
		var userId=userDao.searchId(username);
		map.put("userId",userId);
		var i=userLevelDao.updateExp(map);
		return i;
	}
	
}