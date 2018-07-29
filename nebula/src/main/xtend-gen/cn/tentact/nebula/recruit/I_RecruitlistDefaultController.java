package cn.tentact.nebula.recruit;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_RecruitlistDefaultController {
  /**
   * 招聘列表中倒序查找出五条招聘信息
   */
  public abstract ResponseBean searchRecruitlistDefault(final String token, final Integer s);
}
