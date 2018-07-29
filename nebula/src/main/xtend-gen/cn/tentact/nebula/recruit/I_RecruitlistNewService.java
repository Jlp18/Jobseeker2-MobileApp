package cn.tentact.nebula.recruit;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface I_RecruitlistNewService {
  /**
   * 招聘列表中最新的五条招聘信息
   */
  public abstract List<Map> searchRecruitlistNew(final String username, final Integer d);
}
