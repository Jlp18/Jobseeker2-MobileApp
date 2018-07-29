package cn.tentact.nebula.recruit;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_RecruitDetailsController {
  /**
   * 加载招聘详情页面 MySql
   */
  public abstract ResponseBean RecruitDetails(final String token, final int recruit_id);
  
  /**
   * 加载职位信息 Mongo
   */
  public abstract ResponseBean searchJobMessage(final String token, final int recruit_id);
}
