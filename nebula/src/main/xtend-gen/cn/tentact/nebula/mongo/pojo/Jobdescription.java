package cn.tentact.nebula.mongo.pojo;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * jobdescription表实体类
 */
@Accessors
@SuppressWarnings("all")
public class Jobdescription {
  private String jobname;
  
  private String jobintroduce;
  
  private String require;
  
  @Pure
  public String getJobname() {
    return this.jobname;
  }
  
  public void setJobname(final String jobname) {
    this.jobname = jobname;
  }
  
  @Pure
  public String getJobintroduce() {
    return this.jobintroduce;
  }
  
  public void setJobintroduce(final String jobintroduce) {
    this.jobintroduce = jobintroduce;
  }
  
  @Pure
  public String getRequire() {
    return this.require;
  }
  
  public void setRequire(final String require) {
    this.require = require;
  }
}
