package cn.tentact.nebula.recruit;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_RecruitListController {
  /**
   * 招聘列表中倒序查找出五条招聘信息
   */
  public abstract ResponseBean searchRecruitListDefault(final String token, final Integer s);
  
  /**
   * 招聘列表中最新的五条招聘信息
   */
  public abstract ResponseBean searchRecruitListNew(final String token, final Integer d);
  
  /**
   * 最低月薪
   */
  public abstract ResponseBean searchRecruitListSalarymin(final String token, final Integer smin);
  
  /**
   * 最高月薪
   */
  public abstract ResponseBean searchRecruitListSalarymax(final String token, final Integer smax);
}
