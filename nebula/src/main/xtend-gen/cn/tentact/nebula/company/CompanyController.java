package cn.tentact.nebula.company;

import cn.tentact.nebula.company.I_CompanyController;
import cn.tentact.nebula.company.I_CompanyService;
import cn.tentact.nebula.mongo.pojo.Company;
import cn.tentact.nebula.web.ResponseBean;
import java.util.Collections;
import java.util.Map;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@SuppressWarnings("all")
public class CompanyController implements I_CompanyController {
  @Autowired
  private I_CompanyService companyService;
  
  @RequestMapping("/insert")
  @Override
  public ResponseBean insert(@RequestHeader("Authorization") final String token, final String name, final String tel, final String website, final String scale, final String type, final String resume) {
    Pair<String, String> _mappedTo = Pair.<String, String>of("name", name);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("tel", tel);
    Pair<String, String> _mappedTo_2 = Pair.<String, String>of("website", website);
    Pair<String, String> _mappedTo_3 = Pair.<String, String>of("scale", scale);
    Pair<String, String> _mappedTo_4 = Pair.<String, String>of("type", type);
    Pair<String, String> _mappedTo_5 = Pair.<String, String>of("resume", resume);
    Map<String, String> map = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3, _mappedTo_4, _mappedTo_5));
    this.companyService.insert(map);
    return new ResponseBean(200, "保存成功", true, null);
  }
  
  @RequestMapping("/selectFromMongo")
  @Override
  public ResponseBean selectFromMongo(@RequestHeader("Authorization") final String token, final Integer company_id) {
    Company company = this.companyService.selectFromMongo(company_id);
    return new ResponseBean(200, "查询成功", true, company);
  }
  
  @RequestMapping("/selectFromMysql")
  @Override
  public ResponseBean selectFromMysql(@RequestHeader("Authorization") final String token, final int company_id) {
    Map map = this.companyService.selectFromMysql(company_id);
    return new ResponseBean(200, "查询成功", true, map);
  }
}
