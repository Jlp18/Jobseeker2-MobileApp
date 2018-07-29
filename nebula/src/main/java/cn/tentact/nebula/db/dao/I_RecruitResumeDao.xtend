package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.Map

@Mapper
interface I_RecruitResumeDao {
	/**
	 * 查询我投递简历的数量
	 */
	def long searchMyResumeCount(String username);
	
	/**
	 * 投递简历
	 */
	def int add(Map map);
}