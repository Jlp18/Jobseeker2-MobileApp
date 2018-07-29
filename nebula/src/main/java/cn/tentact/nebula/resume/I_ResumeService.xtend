package cn.tentact.nebula.resume

import java.util.Map

interface I_ResumeService {
	/**
	 * 投递简历
	 */
	def int sendMyResume(Map map);
	/**
	 * 更新简历信息
	 */
	def void updateResumeinfo(Map map);
	/**
	 * 插入简历信息
	 */
	 def void insertResumeinfo(Map map);
	 /**
	  * 查询简历信息
	  */
	 def Map selectResumeinfo(int user_id);	
	 /**
	  * 上传用户头像地址
	  */
	  def void updatePhoto(Map map);
}