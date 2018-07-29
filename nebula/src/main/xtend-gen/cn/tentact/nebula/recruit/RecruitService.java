package cn.tentact.nebula.recruit;

import cn.tentact.nebula.db.dao.I_RecruitDao;
import cn.tentact.nebula.db.dao.I_ResumeDao;
import cn.tentact.nebula.recruit.I_RecruitService;
import com.google.common.base.Objects;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class RecruitService implements I_RecruitService {
  @Autowired
  private I_RecruitDao recruitDao;
  
  @Autowired
  private I_ResumeDao resumeDao;
  
  @Override
  public List<Map> searchCurrentRecruit(final String username) {
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      resumeId = Integer.valueOf((-1));
    }
    List<Map> list = this.recruitDao.searchCurrentRecruit((resumeId).intValue());
    return list;
  }
}
