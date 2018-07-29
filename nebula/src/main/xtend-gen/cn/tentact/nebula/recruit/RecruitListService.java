package cn.tentact.nebula.recruit;

import cn.tentact.nebula.db.dao.I_RecruitListDao;
import cn.tentact.nebula.db.dao.I_ResumeDao;
import cn.tentact.nebula.recruit.I_RecruitListService;
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
public class RecruitListService implements I_RecruitListService {
  @Autowired
  private I_RecruitListDao recruitlistDao;
  
  @Autowired
  private I_ResumeDao resumeDao;
  
  @Override
  public List<Map> searchRecruitListDefault(final String username, final Integer s) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("s", s);
    Map<String, Integer> map = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1));
    List<Map> list = this.recruitlistDao.searchRecruitListDefault(map);
    return list;
  }
  
  @Override
  public List<Map> searchRecruitListNew(final String username, final Integer d) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("d", d);
    Map<String, Integer> map = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1));
    List<Map> list = this.recruitlistDao.searchRecruitListNew(map);
    return list;
  }
  
  @Override
  public List<Map> searchRecruitListSalarymin(final String username, final Integer smin) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("smin", smin);
    Map<String, Integer> map = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1));
    List<Map> list = this.recruitlistDao.searchRecruitListSalarymin(map);
    return list;
  }
  
  @Override
  public List<Map> searchRecruitListSalarymax(final String username, final Integer smax) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("smax", smax);
    Map<String, Integer> map = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1));
    List<Map> list = this.recruitlistDao.searchRecruitListSalarymax(map);
    return list;
  }
}
