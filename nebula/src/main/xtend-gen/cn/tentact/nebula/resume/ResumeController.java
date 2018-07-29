package cn.tentact.nebula.resume;

import cn.tentact.nebula.db.dao.I_UserDao;
import cn.tentact.nebula.resume.I_ResumeController;
import cn.tentact.nebula.resume.I_ResumeService;
import cn.tentact.nebula.shiro.JwtUtil;
import cn.tentact.nebula.web.ResponseBean;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
@SuppressWarnings("all")
public class ResumeController implements I_ResumeController {
  @Autowired
  private I_ResumeService resumeService;
  
  @Autowired
  private I_UserDao userDao;
  
  @RequiresRoles("求职者")
  @RequestMapping("/sendMyResume")
  @Override
  public ResponseBean sendMyResume(@RequestHeader("Authorization") final String token, final int recruitId) {
    String username = JwtUtil.getUsername(token);
    Pair<String, String> _mappedTo = Pair.<String, String>of("username", username);
    Pair<String, Integer> _mappedTo_1 = Pair.<String, Integer>of("recruitId", Integer.valueOf(recruitId));
    HashMap<String, Object> map = CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1);
    int i = this.resumeService.sendMyResume(map);
    return new ResponseBean(200, "投递结果", true, Integer.valueOf(i));
  }
  
  @RequestMapping("/updateResumeinfo")
  @Override
  public ResponseBean updateResumeinfo(@RequestHeader("Authorization") final String token, final String name, final String sex, final String birthday, final String height, final String weight, final String married, final String city, final String education, final String tel, final String email, final String job_city, final String job_salary, final String job_name, final String job_hiredate, final String school, final String major, final String year, final String education_1, final String company_name, final String hiredate, final String leavedate, final String works) {
    String username = JwtUtil.getUsername(token);
    int id = this.userDao.searchId(username);
    Pair<String, String> _mappedTo = Pair.<String, String>of("name", name);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("sex", sex);
    Pair<String, String> _mappedTo_2 = Pair.<String, String>of("birthday", birthday);
    Pair<String, String> _mappedTo_3 = Pair.<String, String>of("height", height);
    Pair<String, String> _mappedTo_4 = Pair.<String, String>of("weight", weight);
    Pair<String, String> _mappedTo_5 = Pair.<String, String>of("married", married);
    Pair<String, String> _mappedTo_6 = Pair.<String, String>of("city", city);
    Pair<String, String> _mappedTo_7 = Pair.<String, String>of("education", education);
    Pair<String, String> _mappedTo_8 = Pair.<String, String>of("tel", tel);
    Pair<String, String> _mappedTo_9 = Pair.<String, String>of("email", email);
    Pair<String, Integer> _mappedTo_10 = Pair.<String, Integer>of("user_id", Integer.valueOf(id));
    Pair<String, String> _mappedTo_11 = Pair.<String, String>of("job_city", job_city);
    Pair<String, String> _mappedTo_12 = Pair.<String, String>of("job_salary", job_salary);
    Pair<String, String> _mappedTo_13 = Pair.<String, String>of("job_name", job_name);
    Pair<String, String> _mappedTo_14 = Pair.<String, String>of("job_hiredate", job_hiredate);
    Pair<String, String> _mappedTo_15 = Pair.<String, String>of("school", school);
    Pair<String, String> _mappedTo_16 = Pair.<String, String>of("major", major);
    Pair<String, String> _mappedTo_17 = Pair.<String, String>of("year", year);
    Pair<String, String> _mappedTo_18 = Pair.<String, String>of("education_1", education_1);
    Pair<String, String> _mappedTo_19 = Pair.<String, String>of("company_name", company_name);
    Pair<String, String> _mappedTo_20 = Pair.<String, String>of("hiredate", hiredate);
    Pair<String, String> _mappedTo_21 = Pair.<String, String>of("leavedate", leavedate);
    Pair<String, String> _mappedTo_22 = Pair.<String, String>of("works", works);
    Map<String, Object> map = Collections.<String, Object>unmodifiableMap(CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3, _mappedTo_4, _mappedTo_5, _mappedTo_6, _mappedTo_7, _mappedTo_8, _mappedTo_9, _mappedTo_10, _mappedTo_11, _mappedTo_12, _mappedTo_13, _mappedTo_14, _mappedTo_15, _mappedTo_16, _mappedTo_17, _mappedTo_18, _mappedTo_19, _mappedTo_20, _mappedTo_21, _mappedTo_22));
    this.resumeService.updateResumeinfo(map);
    return new ResponseBean(200, "简历修改成功", true, null);
  }
  
  @RequestMapping("/insertResumeinfo")
  @Override
  public ResponseBean insertResumeinfo(@RequestHeader("Authorization") final String token, final String name, final String auth, final String sex, final String user_id, final String birthday, final String height, final String weight, final String married, final String city, final String education, final String tel, final String email, final String job_city, final String job_salary, final String job_name, final String job_hiredate, final String school, final String major, final String year, final String education_1, final String company_name, final String hiredate, final String leavedate, final String works) {
    String username = JwtUtil.getUsername(token);
    int id = this.userDao.searchId(username);
    Pair<String, String> _mappedTo = Pair.<String, String>of("name", name);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("auth", auth);
    Pair<String, String> _mappedTo_2 = Pair.<String, String>of("sex", sex);
    Pair<String, String> _mappedTo_3 = Pair.<String, String>of("birthday", birthday);
    Pair<String, String> _mappedTo_4 = Pair.<String, String>of("height", height);
    Pair<String, String> _mappedTo_5 = Pair.<String, String>of("weight", weight);
    Pair<String, String> _mappedTo_6 = Pair.<String, String>of("married", married);
    Pair<String, String> _mappedTo_7 = Pair.<String, String>of("city", city);
    Pair<String, String> _mappedTo_8 = Pair.<String, String>of("education", education);
    Pair<String, String> _mappedTo_9 = Pair.<String, String>of("tel", tel);
    Pair<String, String> _mappedTo_10 = Pair.<String, String>of("email", email);
    Pair<String, Integer> _mappedTo_11 = Pair.<String, Integer>of("user_id", Integer.valueOf(id));
    Pair<String, String> _mappedTo_12 = Pair.<String, String>of("job_city", job_city);
    Pair<String, String> _mappedTo_13 = Pair.<String, String>of("job_salary", job_salary);
    Pair<String, String> _mappedTo_14 = Pair.<String, String>of("job_name", job_name);
    Pair<String, String> _mappedTo_15 = Pair.<String, String>of("job_hiredate", job_hiredate);
    Pair<String, String> _mappedTo_16 = Pair.<String, String>of("school", school);
    Pair<String, String> _mappedTo_17 = Pair.<String, String>of("major", major);
    Pair<String, String> _mappedTo_18 = Pair.<String, String>of("year", year);
    Pair<String, String> _mappedTo_19 = Pair.<String, String>of("education_1", education_1);
    Pair<String, String> _mappedTo_20 = Pair.<String, String>of("company_name", company_name);
    Pair<String, String> _mappedTo_21 = Pair.<String, String>of("hiredate", hiredate);
    Pair<String, String> _mappedTo_22 = Pair.<String, String>of("leavedate", leavedate);
    Pair<String, String> _mappedTo_23 = Pair.<String, String>of("works", works);
    Map<String, Object> map = Collections.<String, Object>unmodifiableMap(CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3, _mappedTo_4, _mappedTo_5, _mappedTo_6, _mappedTo_7, _mappedTo_8, _mappedTo_9, _mappedTo_10, _mappedTo_11, _mappedTo_12, _mappedTo_13, _mappedTo_14, _mappedTo_15, _mappedTo_16, _mappedTo_17, _mappedTo_18, _mappedTo_19, _mappedTo_20, _mappedTo_21, _mappedTo_22, _mappedTo_23));
    this.resumeService.insertResumeinfo(map);
    return new ResponseBean(200, "保存成功", true, null);
  }
  
  @RequestMapping("/selectResumeinfo")
  @Override
  public ResponseBean selectResumeinfo(@RequestHeader("Authorization") final String token) {
    String username = JwtUtil.getUsername(token);
    int id = this.userDao.searchId(username);
    Map map = this.resumeService.selectResumeinfo(id);
    return new ResponseBean(200, "查询成功", true, map);
  }
  
  @RequestMapping("/updatePhoto")
  @Override
  public ResponseBean updatePhoto(@RequestHeader("Authorization") final String token, final String photo_path) {
    String username = JwtUtil.getUsername(token);
    Pair<String, String> _mappedTo = Pair.<String, String>of("photo_path", photo_path);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("username", username);
    Map<String, String> map = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1));
    this.resumeService.updatePhoto(map);
    return new ResponseBean(200, "上传结果", true, null);
  }
}
