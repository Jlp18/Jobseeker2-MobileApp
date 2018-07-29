package cn.tentact.nebula.mongo.pojo;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * company表实体类
 */
@Accessors
@SuppressWarnings("all")
public class Company {
  private String company_id;
  
  private String resume;
  
  @Pure
  public String getCompany_id() {
    return this.company_id;
  }
  
  public void setCompany_id(final String company_id) {
    this.company_id = company_id;
  }
  
  @Pure
  public String getResume() {
    return this.resume;
  }
  
  public void setResume(final String resume) {
    this.resume = resume;
  }
}
