package cn.tentact.nebula.db.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_ResumeDao {
  /**
   * 查询用户简历id
   */
  public abstract Integer searchMyResumeId(final String username);
  
  /**
   * 更新用户简历信息
   */
  public abstract void updateResumeinfo(final Map map);
  
  /**
   * 插入用户简历信息
   */
  public abstract void insertResumeinfo(final Map map);
  
  /**
   * 查询用户简历信息
   */
  public abstract Map selectResumeinfo(final int user_id);
  
  /**
   * 上传用户头像地址
   */
  public abstract void updatePhoto(final Map map);
}
