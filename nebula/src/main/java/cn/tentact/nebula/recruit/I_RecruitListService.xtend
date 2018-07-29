package cn.tentact.nebula.recruit

import java.util.List
import java.util.Map

interface I_RecruitListService {
		/**
		 * 招聘列表中倒序查找出五条招聘信息
	 	*/
	 	def List<Map> searchRecruitListDefault(String username,Integer s);
	 	/**
		 * 招聘列表中最新的五条招聘信息
		 */
		 def List<Map> searchRecruitListNew(String username,Integer d);
		 /**
		 * 最低月薪
		 */
		 def List<Map> searchRecruitListSalarymin(String username,Integer smin);
		 /**
		 * 最高月薪
		 */
		 def List<Map> searchRecruitListSalarymax(String username,Integer smax);
}