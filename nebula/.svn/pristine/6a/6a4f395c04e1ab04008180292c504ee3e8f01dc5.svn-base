package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.List
import java.util.Map

@Mapper
interface I_RecruitListDao {
	 /**
	 * 招聘列表中倒序查找出五条招聘信息
	 */
	 def List<Map> searchRecruitListDefault(Map map);
	 /**
	 * 招聘列表中最新的五条招聘信息
	 */
	 def List<Map> searchRecruitListNew(Map map);
	 /**
	 * 最低月薪
	 */
	 def List<Map> searchRecruitListSalarymin(Map map);
	 /**
	 * 最高月薪
	 */
	 def List<Map> searchRecruitListSalarymax(Map map);
}