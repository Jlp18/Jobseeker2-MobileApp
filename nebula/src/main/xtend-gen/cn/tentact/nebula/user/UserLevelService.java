package cn.tentact.nebula.user;

import cn.tentact.nebula.db.dao.I_UserDao;
import cn.tentact.nebula.db.dao.I_UserLevelDao;
import cn.tentact.nebula.user.I_UserLevelService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("all")
public class UserLevelService implements I_UserLevelService {
  @Autowired
  private I_UserDao userDao;
  
  @Autowired
  private I_UserLevelDao userLevelDao;
  
  @Transactional
  @Override
  public int updateExp(final Map map) {
    Object _get = map.get("username");
    String username = ((String) _get);
    int userId = this.userDao.searchId(username);
    map.put("userId", Integer.valueOf(userId));
    int i = this.userLevelDao.updateExp(map);
    return i;
  }
}
