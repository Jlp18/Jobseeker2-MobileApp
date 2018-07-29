package cn.tentact.nebula.db.search;

import cn.tentact.nebula.db.search.I_SearchListController;
import cn.tentact.nebula.db.search.I_SearchListService;
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
@RequestMapping("/search")
@SuppressWarnings("all")
public class SearchListController implements I_SearchListController {
  @Autowired
  private I_SearchListService searchlistService;
  
  @RequestMapping("/searchListDefault")
  @RequiresRoles("求职者")
  @Override
  public ResponseBean searchListDefault(@RequestHeader("Authorization") final String token, final String content) {
    String username = JwtUtil.getUsername(token);
    List<Map> list = this.searchlistService.searchListDefault(username, content);
    return new ResponseBean(200, "搜索招聘信息", true, list);
  }
  
  @RequestMapping("/searchListNew")
  @RequiresRoles("求职者")
  @Override
  public ResponseBean searchListNew(@RequestHeader("Authorization") final String token, final String content) {
    String username = JwtUtil.getUsername(token);
    List<Map> list = this.searchlistService.searchListNew(username, content);
    return new ResponseBean(200, "搜索的最新招聘信息", true, list);
  }
  
  @RequestMapping("/searchListSalarymin")
  @RequiresRoles("求职者")
  @Override
  public ResponseBean searchListSalarymin(@RequestHeader("Authorization") final String token, final String content) {
    String username = JwtUtil.getUsername(token);
    List<Map> list = this.searchlistService.searchListSalarymin(username, content);
    return new ResponseBean(200, "搜索的按照最低月薪排序的招聘信息", true, list);
  }
  
  @RequestMapping("/searchListSalarymax")
  @RequiresRoles("求职者")
  @Override
  public ResponseBean searchListSalarymax(@RequestHeader("Authorization") final String token, final String content) {
    String username = JwtUtil.getUsername(token);
    List<Map> list = this.searchlistService.searchListSalarymax(username, content);
    return new ResponseBean(200, "搜索的按照最高月薪排序的招聘信息", true, list);
  }
}
