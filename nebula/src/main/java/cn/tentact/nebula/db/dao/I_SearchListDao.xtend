package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.List
import java.util.Map

@Mapper
interface I_SearchListDao {
	/**
	 * 按条件搜索
	 */
	 def List<Map> searchListDefault(Map map);
	 
	 def List<Map> searchListNew(Map map);
	 
	 def List<Map> searchListSalarymin(Map map);
	 
	 def List<Map> searchListSalarymax(Map map);
}