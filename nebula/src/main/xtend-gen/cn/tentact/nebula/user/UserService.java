package cn.tentact.nebula.user;

import cn.tentact.nebula.db.dao.I_RecruitResumeDao;
import cn.tentact.nebula.db.dao.I_UserDao;
import cn.tentact.nebula.db.dao.I_UserLevelDao;
import cn.tentact.nebula.user.I_UserService;
import java.util.Collections;
import java.util.Map;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户模块实现
 */
@Service
@SuppressWarnings("all")
public class UserService implements I_UserService {
  @Autowired
  private I_UserDao userDao;
  
  @Autowired
  private I_UserLevelDao userLevelDao;
  
  @Autowired
  private I_RecruitResumeDao recruitResumeDao;
  
  @Override
  public boolean login(final Map map) {
    long count = this.userDao.login(map);
    if ((count == 1)) {
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public Map searchSummary(final String username) {
    String photoPath = this.userDao.searchPhoto(username);
    int exp = this.userLevelDao.searchExp(username);
    long resumeCount = this.recruitResumeDao.searchMyResumeCount(username);
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("exp", Integer.valueOf(exp));
    Pair<String, Long> _mappedTo_1 = Pair.<String, Long>of("resumeCount", Long.valueOf(resumeCount));
    Pair<String, String> _mappedTo_2 = Pair.<String, String>of("photoPath", photoPath);
    return Collections.<String, Object>unmodifiableMap(CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2));
  }
  
  @Override
  public int register(final Map map) {
    Object _get = map.get("username");
    String username = ((String) _get);
    long j = this.userDao.existIf(username);
    if ((j == 1)) {
      return 0;
    } else {
      int i = this.userDao.addUser(map);
      this.userDao.addUserLevel();
      return i;
    }
  }
  
  @Override
  public int updatePassword(final Map map) {
    int i = this.userDao.insertNewPassword(map);
    return i;
  }
}
