package cn.tentact.nebula.company;

import cn.tentact.nebula.company.I_CompanyService;
import cn.tentact.nebula.db.dao.I_CompanyDao;
import cn.tentact.nebula.mongo.dao.I_CompanyMongoDao;
import cn.tentact.nebula.mongo.pojo.Company;
import java.util.Collections;
import java.util.Map;
import org.bson.Document;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("all")
public class CompanyService implements I_CompanyService {
  @Autowired
  private I_CompanyMongoDao companyMongoDao;
  
  @Autowired
  private I_CompanyDao companyDao;
  
  @Transactional
  @Override
  public void insert(final Map map) {
    Object _get = map.get("name");
    Pair<String, Object> _mappedTo = Pair.<String, Object>of("name", _get);
    Object _get_1 = map.get("tel");
    Pair<String, Object> _mappedTo_1 = Pair.<String, Object>of("tel", _get_1);
    Object _get_2 = map.get("website");
    Pair<String, Object> _mappedTo_2 = Pair.<String, Object>of("website", _get_2);
    Object _get_3 = map.get("scale");
    Pair<String, Object> _mappedTo_3 = Pair.<String, Object>of("scale", _get_3);
    Object _get_4 = map.get("type");
    Pair<String, Object> _mappedTo_4 = Pair.<String, Object>of("type", _get_4);
    Map<String, Object> one = Collections.<String, Object>unmodifiableMap(CollectionLiterals.<String, Object>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3, _mappedTo_4));
    this.companyDao.insert(one);
    Object _get_5 = map.get("name");
    int id = this.companyDao.searchIdByName(((String) _get_5));
    Document document = new Document();
    document.put("company_id", Integer.valueOf(id));
    Object _get_6 = map.get("resume");
    document.put("resume", ((String) _get_6));
    this.companyMongoDao.insert(document);
  }
  
  @Override
  public Company selectFromMongo(final Integer company_id) {
    return this.companyMongoDao.selectFromMongo(company_id);
  }
  
  @Override
  public Map selectFromMysql(final int company_id) {
    Map map = this.companyDao.selectFromMysql(company_id);
    return map;
  }
}
