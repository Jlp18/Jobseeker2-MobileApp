package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.List
import java.util.Map

@Mapper
interface I_RecruitlistNewDao {
	/**
	 * 招聘列表中最新的五条招聘信息
	 */
	 def List<Map> searchRecruitlistNew(Map map);
}