package cn.tentact.nebula.recruit;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface I_RecruitService {
  /**
   * 查询最新5条招聘信息
   */
  public abstract List<Map> searchCurrentRecruit(final String username);
}
