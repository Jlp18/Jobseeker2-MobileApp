package cn.tentact.nebula.user;

import cn.tentact.nebula.shiro.JwtUtil;
import cn.tentact.nebula.user.I_UserLevelController;
import cn.tentact.nebula.user.I_UserLevelService;
import cn.tentact.nebula.web.ResponseBean;
import java.util.HashMap;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userlevel")
@SuppressWarnings("all")
public class UserLevelController implements I_UserLevelController {
  @Autowired
  private I_UserLevelService userLevelService;
  
  @RequiresRoles("求职者")
  @RequestMapping("/updateExp_1")
  @Override
  public ResponseBean updateExp_1(@RequestHeader("Authorization") final String token) {
    String username = JwtUtil.getUsername(token);
    Pair<String, String> _mappedTo = Pair.<String, String>of("username", username);
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("exp", Integer.valueOf(10));
    HashMap<String, Object> map = CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1);
    int i = this.userLevelService.updateExp(map);
    return new ResponseBean(200, "更改结果", true, Integer.valueOf(i));
  }
}
