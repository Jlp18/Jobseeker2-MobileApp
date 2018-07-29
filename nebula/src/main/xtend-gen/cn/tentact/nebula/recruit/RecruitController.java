package cn.tentact.nebula.recruit;

import cn.tentact.nebula.recruit.I_RecruitController;
import cn.tentact.nebula.recruit.I_RecruitService;
import cn.tentact.nebula.shiro.JwtUtil;
import cn.tentact.nebula.web.ResponseBean;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruit")
@SuppressWarnings("all")
public class RecruitController implements I_RecruitController {
  @Autowired
  private I_RecruitService recruitService;
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchCurrentRecruit")
  @Override
  public ResponseBean searchCurrentRecruit(@RequestHeader("Authorization") final String token) {
    String username = JwtUtil.getUsername(token);
    List<Map> list = this.recruitService.searchCurrentRecruit(username);
    return new ResponseBean(200, "最新招聘信息", true, list);
  }
}
