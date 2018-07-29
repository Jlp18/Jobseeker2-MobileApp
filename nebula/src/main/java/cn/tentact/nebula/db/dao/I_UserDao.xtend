package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.Map

@Mapper
interface I_UserDao {
	
	def long login(Map map);
	
	def int searchId(String username);
	def String searchEmail(String username);  //查询用户email
	def int insertNewPassword(Map map); //更新密码
	def String searchPhoto(String username); //查询头像地址
	def int addUser(Map map);
	def void addUserLevel();
	def void updatePhoto(Map map); //更新头像地址
	def long existIf(String username);
}