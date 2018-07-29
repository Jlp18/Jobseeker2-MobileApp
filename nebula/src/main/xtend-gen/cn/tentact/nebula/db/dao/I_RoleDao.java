package cn.tentact.nebula.db.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_RoleDao {
  /**
   * 查询用户角色
   */
  public abstract String searchUserRole(final String username);
}
