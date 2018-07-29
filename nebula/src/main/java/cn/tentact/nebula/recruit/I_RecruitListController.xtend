package cn.tentact.nebula.recruit

import cn.tentact.nebula.web.ResponseBean

interface I_RecruitListController {
		/**
		 * 招聘列表中倒序查找出五条招聘信息
	 	*/
		def ResponseBean searchRecruitListDefault(String token,Integer s);
		/**
	 	* 招聘列表中最新的五条招聘信息
	 	*/
	 	def ResponseBean searchRecruitListNew(String token,Integer d);
	 	/**
	 	* 最低月薪
	 	*/
	 	def ResponseBean searchRecruitListSalarymin(String token,Integer smin);
	 	/**
	 	* 最高月薪
	 	*/
	 	def ResponseBean searchRecruitListSalarymax(String token,Integer smax);
}