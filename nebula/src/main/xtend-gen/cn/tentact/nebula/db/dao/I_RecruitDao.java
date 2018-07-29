package cn.tentact.nebula.db.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_RecruitDao {
  /**
   * 查询最新5条招聘信息
   */
  public abstract List<Map> searchCurrentRecruit(final int resumeId);
}
