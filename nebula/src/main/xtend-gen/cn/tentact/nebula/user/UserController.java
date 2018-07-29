package cn.tentact.nebula.user;

import cn.tentact.nebula.shiro.JwtUtil;
import cn.tentact.nebula.user.I_UserController;
import cn.tentact.nebula.user.I_UserService;
import cn.tentact.nebula.web.ResponseBean;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块网络类
 */
@RestController
@RequestMapping("/user")
@SuppressWarnings("all")
public class UserController implements I_UserController {
  @Qualifier("redisTemplate")
  @Autowired
  private RedisTemplate redis;
  
  @Autowired
  private I_UserService userService;
  
  @RequestMapping("/login")
  @Override
  public ResponseBean login(final String username, final String password) {
    Pair<String, String> _mappedTo = Pair.<String, String>of("username", username);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("password", password);
    boolean bool = this.userService.login(Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1)));
    if (bool) {
      String token = JwtUtil.sign(username, password);
      Pair<String, String> _mappedTo_2 = Pair.<String, String>of("token", token);
      Pair<String, String> _mappedTo_3 = Pair.<String, String>of("password", password);
      this.redis.<Object, Object>opsForHash().putAll(username, Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo_2, _mappedTo_3)));
      this.redis.expire(username, JwtUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS);
      Pair<String, String> _mappedTo_4 = Pair.<String, String>of("token", token);
      return new ResponseBean(200, "登录成功", bool, Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo_4)));
    } else {
      return new ResponseBean(200, "登录失败", bool, null);
    }
  }
  
  @RequestMapping("/searchSummary")
  @RequiresRoles("求职者")
  @Override
  public ResponseBean searchSummary(@RequestHeader("Authorization") final String token) {
    String username = JwtUtil.getUsername(token);
    Map map = this.userService.searchSummary(username);
    return new ResponseBean(200, "查询成功", true, map);
  }
  
  @RequestMapping("/register")
  @Override
  public ResponseBean register(final String username, final String password, final String email, final int roleId) {
    Pair<String, String> _mappedTo = Pair.<String, String>of("username", username);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("password", password);
    Pair<String, String> _mappedTo_2 = Pair.<String, String>of("email", email);
    Pair<String, Integer> _mappedTo_3 = Pair.<String, Integer>of("userRole", Integer.valueOf(roleId));
    int bool = this.userService.register(Collections.<String, Object>unmodifiableMap(CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3)));
    return new ResponseBean(200, "注册成功", true, Integer.valueOf(bool));
  }
  
  @RequestMapping("/updatePassword")
  @Override
  public ResponseBean updatePassword(final String username, final String password) {
    Pair<String, String> _mappedTo = Pair.<String, String>of("username", username);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("password", password);
    Map<String, String> map = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1));
    int i = this.userService.updatePassword(map);
    return new ResponseBean(200, "更改结果", true, Integer.valueOf(i));
  }
}
