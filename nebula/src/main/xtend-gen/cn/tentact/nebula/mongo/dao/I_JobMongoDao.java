package cn.tentact.nebula.mongo.dao;

import cn.tentact.nebula.mongo.pojo.Jobdescription;

@SuppressWarnings("all")
public interface I_JobMongoDao {
  public abstract Jobdescription searchJobMessage(final String job_name);
}
