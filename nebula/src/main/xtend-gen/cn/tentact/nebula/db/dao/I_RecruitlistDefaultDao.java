package cn.tentact.nebula.db.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_RecruitlistDefaultDao {
  /**
   * 招聘列表中倒序查找出五条招聘信息
   */
  public abstract List<Map> searchRecruitlistDefault(final Map map);
}
