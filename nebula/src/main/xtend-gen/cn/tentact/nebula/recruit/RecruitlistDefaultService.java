package cn.tentact.nebula.recruit;

import cn.tentact.nebula.db.dao.I_RecruitlistDefaultDao;
import cn.tentact.nebula.db.dao.I_ResumeDao;
import cn.tentact.nebula.recruit.I_RecruitlistDefaultService;
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
public class RecruitlistDefaultService implements I_RecruitlistDefaultService {
  @Autowired
  private I_RecruitlistDefaultDao recruitlistdefaultDao;
  
  @Autowired
  private I_ResumeDao resumeDao;
  
  @Override
  public List<Map> searchRecruitlistDefault(final String username, final Integer s) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("s", s);
    Map<String, Integer> map = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1));
    List<Map> list = this.recruitlistdefaultDao.searchRecruitlistDefault(map);
    return list;
  }
}
