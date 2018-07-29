package cn.tentact.nebula.recruit;

import cn.tentact.nebula.mongo.pojo.Jobdescription;
import cn.tentact.nebula.recruit.I_RecruitDetailsController;
import cn.tentact.nebula.recruit.I_RecruitDetailsService;
import cn.tentact.nebula.web.ResponseBean;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruitDetails")
@SuppressWarnings("all")
public class RecruitDetailsController implements I_RecruitDetailsController {
  @Autowired
  private I_RecruitDetailsService recruitDetailsService;
  
  @RequiresRoles("求职者")
  @RequestMapping("/fromMysql")
  @Override
  public ResponseBean RecruitDetails(@RequestHeader("Authorization") final String token, final int recruit_id) {
    Map map = this.recruitDetailsService.RecruitDetails(recruit_id);
    return new ResponseBean(200, "招聘信息详情", true, map);
  }
  
  @RequiresRoles("求职者")
  @RequestMapping("/fromMongo")
  @Override
  public ResponseBean searchJobMessage(@RequestHeader("Authorization") final String token, final int recruit_id) {
    Jobdescription job = this.recruitDetailsService.searchJobMessage(recruit_id);
    return new ResponseBean(200, "职位介绍详情", true, job);
  }
}
