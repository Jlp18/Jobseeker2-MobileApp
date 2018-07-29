package cn.tentact.nebula.db.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_RecruitDetailsDao {
  /**
   * 加载招聘详情页面
   */
  public abstract Map recruitDetails(final int recruit_id);
  
  /**
   * 通过recruit id查询职位名称
   */
  public abstract String searchJobnameByRecruitId(final int recruit_id);
}
