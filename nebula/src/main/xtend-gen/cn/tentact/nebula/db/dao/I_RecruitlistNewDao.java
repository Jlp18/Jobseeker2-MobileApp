package cn.tentact.nebula.db.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_RecruitlistNewDao {
  /**
   * 招聘列表中最新的五条招聘信息
   */
  public abstract List<Map> searchRecruitlistNew(final Map map);
}
