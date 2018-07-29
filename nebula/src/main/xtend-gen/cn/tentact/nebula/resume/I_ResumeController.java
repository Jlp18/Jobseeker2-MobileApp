package cn.tentact.nebula.resume;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_ResumeController {
  /**
   * 投递简历
   */
  public abstract ResponseBean sendMyResume(final String token, final int recruitId);
  
  /**
   * 更新简历信息
   */
  public abstract ResponseBean updateResumeinfo(final String token, final String name, final String sex, final String birthday, final String height, final String weight, final String married, final String city, final String education, final String tel, final String email, final String job_city, final String job_salary, final String job_name, final String job_hiredate, final String school, final String major, final String year, final String education_1, final String company_name, final String hiredate, final String leavedate, final String works);
  
  /**
   * 插入简历信息
   */
  public abstract ResponseBean insertResumeinfo(final String token, final String name, final String auth, final String sex, final String user_id, final String birthday, final String height, final String weight, final String married, final String city, final String education, final String tel, final String email, final String job_city, final String job_salary, final String job_name, final String job_hiredate, final String school, final String major, final String year, final String education_1, final String company_name, final String hiredate, final String leavedate, final String works);
  
  /**
   * 查询简历信息
   */
  public abstract ResponseBean selectResumeinfo(final String token);
  
  /**
   * 上传用户头像地址
   */
  public abstract ResponseBean updatePhoto(final String token, final String photo_path);
}
