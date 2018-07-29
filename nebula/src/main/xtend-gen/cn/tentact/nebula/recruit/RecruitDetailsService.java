package cn.tentact.nebula.recruit;

import cn.tentact.nebula.db.dao.I_RecruitDetailsDao;
import cn.tentact.nebula.mongo.dao.I_JobMongoDao;
import cn.tentact.nebula.mongo.pojo.Jobdescription;
import cn.tentact.nebula.recruit.I_RecruitDetailsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class RecruitDetailsService implements I_RecruitDetailsService {
  @Autowired
  private I_RecruitDetailsDao recruitDetailsDao;
  
  @Autowired
  private I_JobMongoDao jobMongoDao;
  
  @Override
  public Map RecruitDetails(final int recruit_id) {
    Map map = this.recruitDetailsDao.recruitDetails(recruit_id);
    return map;
  }
  
  @Override
  public Jobdescription searchJobMessage(final int recruit_id) {
    String job_name = this.recruitDetailsDao.searchJobnameByRecruitId(recruit_id);
    return this.jobMongoDao.searchJobMessage(job_name);
  }
}
