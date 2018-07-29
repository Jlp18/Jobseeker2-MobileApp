package cn.tentact.nebula.resume;

import cn.tentact.nebula.db.dao.I_RecruitResumeDao;
import cn.tentact.nebula.db.dao.I_ResumeDao;
import cn.tentact.nebula.db.dao.I_UserDao;
import cn.tentact.nebula.resume.I_ResumeService;
import com.google.common.base.Objects;
import java.util.Collections;
import java.util.Map;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("all")
public class ResumeService implements I_ResumeService {
  @Autowired
  private I_UserDao userDao;
  
  @Autowired
  private I_ResumeDao resumeDao;
  
  @Autowired
  private I_RecruitResumeDao recruitResumeDao;
  
  @Transactional
  @Override
  public int sendMyResume(final Map map) {
    Object _get = map.get("username");
    String username = ((String) _get);
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      return (-1);
    }
    map.put("resumeId", resumeId);
    int i = this.recruitResumeDao.add(map);
    return i;
  }
  
  @Override
  public void updateResumeinfo(final Map map) {
    this.resumeDao.updateResumeinfo(map);
  }
  
  @Override
  public void insertResumeinfo(final Map map) {
    this.resumeDao.insertResumeinfo(map);
  }
  
  @Override
  public Map selectResumeinfo(final int user_id) {
    Map map = this.resumeDao.selectResumeinfo(user_id);
    return map;
  }
  
  @Override
  public void updatePhoto(final Map map) {
    Object _get = map.get("photo_path");
    String photo_path = ((String) _get);
    Object _get_1 = map.get("username");
    String username = ((String) _get_1);
    int user_id = this.userDao.searchId(username);
    Pair<String, String> _mappedTo = Pair.<String, String>of("photo_path", photo_path);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("username", username);
    Map<String, String> one = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1));
    this.userDao.updatePhoto(one);
    Pair<String, String> _mappedTo_2 = Pair.<String, String>of("photo_path", photo_path);
    Pair<String, Integer> _mappedTo_3 = Pair.<String, Integer>of("user_id", Integer.valueOf(user_id));
    Map<String, Object> two = Collections.<String, Object>unmodifiableMap(CollectionLiterals.<String, Object>newHashMap(_mappedTo_2, _mappedTo_3));
    this.resumeDao.updatePhoto(two);
  }
}
