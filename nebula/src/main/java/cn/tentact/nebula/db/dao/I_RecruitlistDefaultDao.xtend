package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.List
import java.util.Map

@Mapper
interface I_RecruitlistDefaultDao {
	/**
	 * 招聘列表中倒序查找出五条招聘信息
	 */
	 def List<Map> searchRecruitlistDefault(Map map);
}