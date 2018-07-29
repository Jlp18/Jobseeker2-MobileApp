package cn.tentact.nebula.mongo.dao

import cn.tentact.nebula.mongo.pojo.Jobdescription

interface I_JobMongoDao {
	def Jobdescription searchJobMessage(String job_name);
}