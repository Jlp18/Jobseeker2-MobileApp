package cn.tentact.nebula.company;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_CompanyController {
  public abstract ResponseBean insert(final String token, final String name, final String tel, final String website, final String scale, final String type, final String resume);
  
  public abstract ResponseBean selectFromMongo(final String token, final Integer company_id);
  
  public abstract ResponseBean selectFromMysql(final String token, final int company_id);
}
