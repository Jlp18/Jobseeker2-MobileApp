package cn.tentact.nebula.db.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_RecruitResumeDao {
  /**
   * 查询我投递简历的数量
   */
  public abstract long searchMyResumeCount(final String username);
  
  /**
   * 投递简历
   */
  public abstract int add(final Map map);
}
