package cn.tentact.nebula.recruit;

import cn.tentact.nebula.mongo.pojo.Jobdescription;
import java.util.Map;

@SuppressWarnings("all")
public interface I_RecruitDetailsService {
  public abstract Map RecruitDetails(final int recruit_id);
  
  public abstract Jobdescription searchJobMessage(final int recruit_id);
}
