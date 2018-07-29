package cn.tentact.nebula.recruit;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_RecruitlistNewController {
  /**
   * 招聘列表中最新的五条招聘信息
   */
  public abstract ResponseBean searchRecruitlistNew(final String token, final Integer d);
}
