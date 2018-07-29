package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.Map

@Mapper
interface I_ResumeDao {
	/**
	 * 查询用户简历id
	 */
	def Integer searchMyResumeId(String username);
	/**
	 * 更新用户简历信息
	 */
	 def void updateResumeinfo(Map map);
	 /**
	  * 插入用户简历信息
	  */
	  def void insertResumeinfo(Map map);
	 /**
	  * 查询用户简历信息
	  */
	 def Map selectResumeinfo(int user_id);
	 /**
	  * 上传用户头像地址
	  */
	  def void updatePhoto(Map map);
}