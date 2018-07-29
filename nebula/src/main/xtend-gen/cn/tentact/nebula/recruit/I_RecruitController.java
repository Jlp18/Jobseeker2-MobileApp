package cn.tentact.nebula.recruit;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_RecruitController {
  /**
   * 查询最新5条招聘信息
   */
  public abstract ResponseBean searchCurrentRecruit(final String token);
}
