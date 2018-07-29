package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.Map

@Mapper
interface I_UserLevelDao {
	def int searchExp(String username);
	
	def int updateExp(Map map);
}