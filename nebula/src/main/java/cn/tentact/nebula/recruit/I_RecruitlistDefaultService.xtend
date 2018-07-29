package cn.tentact.nebula.recruit

import java.util.List
import java.util.Map

interface I_RecruitlistDefaultService {
	/**
	 * 招聘列表中倒序查找出五条招聘信息
	 */
	 def List<Map> searchRecruitlistDefault(String username,Integer s);
}