package cn.tentact.nebula.recruit;

import cn.tentact.nebula.recruit.I_RecruitlistDefaultController;
import cn.tentact.nebula.recruit.I_RecruitlistDefaultService;
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
public class RecruitlistDefaultController implements I_RecruitlistDefaultController {
  @Autowired
  private I_RecruitlistDefaultService recruitlistdefaultService;
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchRecruitlistDefault")
  @Override
  public ResponseBean searchRecruitlistDefault(@RequestHeader("Authorization") final String token, final Integer s) {
    String username = JwtUtil.getUsername(token);
    List<Map> list = this.recruitlistdefaultService.searchRecruitlistDefault(username, s);
    return new ResponseBean(200, "招聘信息", true, list);
  }
}
