package cn.tentact.nebula.db.search

import java.util.List
import java.util.Map

interface I_SearchListService {
	/**
	 * 按条件搜索
	 */
	 def List<Map> searchListDefault(String username,String content);
	 def List<Map> searchListNew(String username,String content);
	 def List<Map> searchListSalarymin(String username,String content);
	 def List<Map> searchListSalarymax(String username,String content);
}