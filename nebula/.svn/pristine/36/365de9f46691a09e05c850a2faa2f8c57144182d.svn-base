package cn.tentact.nebula.role

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_RoleDao

@Service
class RoleService implements I_RoleService {
	@Autowired
	I_RoleDao roleDao;
	
	override searchUserRole(String username){
		var role=roleDao.searchUserRole(username);
		return role;
	}
}