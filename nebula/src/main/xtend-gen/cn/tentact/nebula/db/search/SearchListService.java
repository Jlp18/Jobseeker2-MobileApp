package cn.tentact.nebula.db.search;

import cn.tentact.nebula.db.dao.I_ResumeDao;
import cn.tentact.nebula.db.dao.I_SearchListDao;
import cn.tentact.nebula.db.search.I_SearchListService;
import com.google.common.base.Objects;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class SearchListService implements I_SearchListService {
  @Autowired
  private I_SearchListDao searchlistDao;
  
  @Autowired
  private I_ResumeDao resumeDao;
  
  @Override
  public List<Map> searchListDefault(final String username, final String content) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("content", content);
    Map<String, Object> map = Collections.<String, Object>unmodifiableMap(CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1));
    List<Map> list = this.searchlistDao.searchListDefault(map);
    return list;
  }
  
  @Override
  public List<Map> searchListNew(final String username, final String content) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("content", content);
    Map<String, Object> map = Collections.<String, Object>unmodifiableMap(CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1));
    List<Map> list = this.searchlistDao.searchListNew(map);
    return list;
  }
  
  @Override
  public List<Map> searchListSalarymin(final String username, final String content) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("content", content);
    Map<String, Object> map = Collections.<String, Object>unmodifiableMap(CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1));
    List<Map> list = this.searchlistDao.searchListSalarymin(map);
    return list;
  }
  
  @Override
  public List<Map> searchListSalarymax(final String username, final String content) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("content", content);
    Map<String, Object> map = Collections.<String, Object>unmodifiableMap(CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1));
    List<Map> list = this.searchlistDao.searchListSalarymax(map);
    return list;
  }
}
