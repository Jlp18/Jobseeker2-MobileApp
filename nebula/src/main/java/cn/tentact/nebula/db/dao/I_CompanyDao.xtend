package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.Map

@Mapper
interface I_CompanyDao {
	def void insert(Map map);
	def int searchIdByName(String name);
	
	def Map selectFromMysql(int company_id);
}