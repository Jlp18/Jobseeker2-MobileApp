package cn.tentact.nebula.recruit;

import cn.tentact.nebula.db.dao.I_RecruitlistNewDao;
import cn.tentact.nebula.db.dao.I_ResumeDao;
import cn.tentact.nebula.recruit.I_RecruitlistNewService;
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
public class RecruitlistNewService implements I_RecruitlistNewService {
  @Autowired
  private I_RecruitlistNewDao recruitlistnewDao;
  
  @Autowired
  private I_ResumeDao resumeDao;
  
  @Override
  public List<Map> searchRecruitlistNew(final String username, final Integer d) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("resumeId", resumeId);
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("d", d);
    Map<String, Integer> map = Collections.<String, Integer>unmodifiableMap(CollectionLiterals.<String, Integer>newHashMap(_mappedTo, _mappedTo_1));
    List<Map> list = this.recruitlistnewDao.searchRecruitlistNew(map);
    return list;
  }
}
