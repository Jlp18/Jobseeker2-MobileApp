package cn.tentact.nebula.recruit

import cn.tentact.nebula.web.ResponseBean

interface I_RecruitlistDefaultController {
	/**
	 * 招聘列表中倒序查找出五条招聘信息
	 */
	def ResponseBean searchRecruitlistDefault(String token,Integer s);
}