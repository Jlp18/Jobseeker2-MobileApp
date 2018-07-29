package cn.tentact.nebula.recruit;

import cn.tentact.nebula.recruit.I_RecruitListController;
import cn.tentact.nebula.recruit.I_RecruitListService;
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
public class RecruitListController implements I_RecruitListController {
  @Autowired
  private I_RecruitListService recruitlistService;
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchRecruitListDefault")
  @Override
  public ResponseBean searchRecruitListDefault(@RequestHeader("Authorization") final String token, final Integer s) {
    String username = JwtUtil.getUsername(token);
    List<Map> list = this.recruitlistService.searchRecruitListDefault(username, s);
    return new ResponseBean(200, "招聘信息", true, list);
  }
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchRecruitListNew")
  @Override
  public ResponseBean searchRecruitListNew(@RequestHeader("Authorization") final String token, final Integer d) {
    String username = JwtUtil.getUsername(token);
    List<Map> list = this.recruitlistService.searchRecruitListNew(username, d);
    return new ResponseBean(200, "招聘信息", true, list);
  }
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchRecruitListSalarymin")
  @Override
  public ResponseBean searchRecruitListSalarymin(@RequestHeader("Authorization") final String token, final Integer smin) {
    String username = JwtUtil.getUsername(token);
    List<Map> list = this.recruitlistService.searchRecruitListSalarymin(username, smin);
    return new ResponseBean(200, "最低月薪排序", true, list);
  }
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchRecruitListSalarymax")
  @Override
  public ResponseBean searchRecruitListSalarymax(@RequestHeader("Authorization") final String token, final Integer smax) {
    String username = JwtUtil.getUsername(token);
    List<Map> list = this.recruitlistService.searchRecruitListSalarymax(username, smax);
    return new ResponseBean(200, "最高月薪排序", true, list);
  }
}
