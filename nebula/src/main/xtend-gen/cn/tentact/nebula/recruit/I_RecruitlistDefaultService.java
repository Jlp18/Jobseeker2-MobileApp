package cn.tentact.nebula.recruit;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface I_RecruitlistDefaultService {
  /**
   * 招聘列表中倒序查找出五条招聘信息
   */
  public abstract List<Map> searchRecruitlistDefault(final String username, final Integer s);
}
