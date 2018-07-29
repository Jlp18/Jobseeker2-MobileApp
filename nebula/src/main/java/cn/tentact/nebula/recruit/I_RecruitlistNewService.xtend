package cn.tentact.nebula.recruit

import java.util.List
import java.util.Map

interface I_RecruitlistNewService {
	/**
	 * 招聘列表中最新的五条招聘信息
	 */
	 def List<Map> searchRecruitlistNew(String username,Integer d);
}