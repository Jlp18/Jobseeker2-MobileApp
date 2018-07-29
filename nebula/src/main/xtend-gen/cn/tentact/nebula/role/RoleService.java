package cn.tentact.nebula.role;

import cn.tentact.nebula.db.dao.I_RoleDao;
import cn.tentact.nebula.role.I_RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class RoleService implements I_RoleService {
  @Autowired
  private I_RoleDao roleDao;
  
  @Override
  public String searchUserRole(final String username) {
    String role = this.roleDao.searchUserRole(username);
    return role;
  }
}
