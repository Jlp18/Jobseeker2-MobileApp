package cn.tentact.nebula.resume;

import java.util.Map;

@SuppressWarnings("all")
public interface I_ResumeService {
  /**
   * 投递简历
   */
  public abstract int sendMyResume(final Map map);
  
  /**
   * 更新简历信息
   */
  public abstract void updateResumeinfo(final Map map);
  
  /**
   * 插入简历信息
   */
  public abstract void insertResumeinfo(final Map map);
  
  /**
   * 查询简历信息
   */
  public abstract Map selectResumeinfo(final int user_id);
  
  /**
   * 上传用户头像地址
   */
  public abstract void updatePhoto(final Map map);
}
