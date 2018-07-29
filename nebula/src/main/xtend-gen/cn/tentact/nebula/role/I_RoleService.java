package cn.tentact.nebula.role;

@SuppressWarnings("all")
public interface I_RoleService {
  /**
   * 查询用户角色
   */
  public abstract String searchUserRole(final String username);
}
