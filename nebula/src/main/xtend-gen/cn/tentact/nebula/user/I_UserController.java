package cn.tentact.nebula.user;

import cn.tentact.nebula.web.ResponseBean;

/**
 * 用户模块网络接口
 */
@SuppressWarnings("all")
public interface I_UserController {
  /**
   * 用户登录
   */
  public abstract ResponseBean login(final String username, final String password);
  
  /**
   * 查询用户摘要信息
   */
  public abstract ResponseBean searchSummary(final String token);
  
  /**
   * 用户注册
   */
  public abstract ResponseBean register(final String username, final String password, final String email, final int roleId);
  
  /**
   * 修改密码
   */
  public abstract ResponseBean updatePassword(final String username, final String password);
}
