package cn.tentact.nebula.recruit;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface I_RecruitListService {
  /**
   * 招聘列表中倒序查找出五条招聘信息
   */
  public abstract List<Map> searchRecruitListDefault(final String username, final Integer s);
  
  /**
   * 招聘列表中最新的五条招聘信息
   */
  public abstract List<Map> searchRecruitListNew(final String username, final Integer d);
  
  /**
   * 最低月薪
   */
  public abstract List<Map> searchRecruitListSalarymin(final String username, final Integer smin);
  
  /**
   * 最高月薪
   */
  public abstract List<Map> searchRecruitListSalarymax(final String username, final Integer smax);
}
