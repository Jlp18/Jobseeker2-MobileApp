package cn.tentact.nebula.resume

import cn.tentact.nebula.web.ResponseBean

interface I_ResumeController {
	/**
	 * 投递简历
	 */
	 def ResponseBean sendMyResume(String token,int recruitId);
	 /**
	  * 更新简历信息
	  */
	 def ResponseBean updateResumeinfo(String token,String name,String sex,String birthday,String height,String weight,String married,String city,String education,String tel,String email,String job_city,String job_salary,String job_name,String job_hiredate,String school,String major,String year,String education_1,String company_name,String hiredate,String leavedate,String works);
	 /**
	  * 插入简历信息
	  */
	 def ResponseBean insertResumeinfo(String token,String name,String auth,String sex,String user_id,String birthday,String height,String weight,String married,String city,String education,String tel,String email,String job_city,String job_salary,String job_name,String job_hiredate,String school,String major,String year,String education_1,String company_name,String hiredate,String leavedate,String works);
	 /**
	  * 查询简历信息
	  */
	 def ResponseBean selectResumeinfo(String token);
	 /**
	  * 上传用户头像地址
	  */
	  def ResponseBean updatePhoto(String token,String photo_path);
}