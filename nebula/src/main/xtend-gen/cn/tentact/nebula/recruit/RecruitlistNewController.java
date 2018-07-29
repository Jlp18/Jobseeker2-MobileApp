package cn.tentact.nebula.recruit;

import cn.tentact.nebula.recruit.I_RecruitlistNewController;
import cn.tentact.nebula.recruit.I_RecruitlistNewService;
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
public class RecruitlistNewController implements I_RecruitlistNewController {
  @Autowired
  private I_RecruitlistNewService recruitlistnewService;
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchRecruitlistNew")
  @Override
  public ResponseBean searchRecruitlistNew(@RequestHeader("Authorization") final String token, final Integer d) {
    String username = JwtUtil.getUsername(token);
    List<Map> list = this.recruitlistnewService.searchRecruitlistNew(username, d);
    return new ResponseBean(200, "招聘信息", true, list);
  }
}
