package cn.tentact.nebula.db.dao

import java.util.Map
import org.apache.ibatis.annotations.Mapper

@Mapper
interface I_RecruitDetailsDao {
	/**
	 * 加载招聘详情页面
	 */
	 def Map recruitDetails(int recruit_id);
	 
	 /**
	  * 通过recruit id查询职位名称
	  */
	  def String searchJobnameByRecruitId(int recruit_id);
}