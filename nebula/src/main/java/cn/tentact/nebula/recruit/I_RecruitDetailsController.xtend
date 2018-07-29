package cn.tentact.nebula.recruit

import cn.tentact.nebula.web.ResponseBean

interface I_RecruitDetailsController {
	/**
	 * 加载招聘详情页面 MySql
	 */
	 def ResponseBean RecruitDetails(String token,int recruit_id);
	 
	 /**
	  * 加载职位信息 Mongo
	  */
	  def ResponseBean searchJobMessage(String token,int recruit_id);
}