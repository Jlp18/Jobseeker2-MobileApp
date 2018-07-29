package cn.tentact.nebula.user;

import java.util.Map;

/**
 * 用户模块业务接口
 */
@SuppressWarnings("all")
public interface I_UserService {
  /**
   * 用户登录
   */
  public abstract boolean login(final Map map);
  
  /**
   * 查询用户概要信息
   */
  public abstract Map searchSummary(final String username);
  
  /**
   * 用户注册
   */
  public abstract int register(final Map map);
  
  /**
   * 修改密码
   */
  public abstract int updatePassword(final Map map);
}
