package cn.tentact.nebula.db.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_RecruitListDao {
  /**
   * 招聘列表中倒序查找出五条招聘信息
   */
  public abstract List<Map> searchRecruitListDefault(final Map map);
  
  /**
   * 招聘列表中最新的五条招聘信息
   */
  public abstract List<Map> searchRecruitListNew(final Map map);
  
  /**
   * 最低月薪
   */
  public abstract List<Map> searchRecruitListSalarymin(final Map map);
  
  /**
   * 最高月薪
   */
  public abstract List<Map> searchRecruitListSalarymax(final Map map);
}
