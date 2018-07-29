package cn.tentact.nebula.recruit

import java.util.Map
import cn.tentact.nebula.mongo.pojo.Jobdescription

interface I_RecruitDetailsService {
	def Map RecruitDetails(int recruit_id);
	def Jobdescription searchJobMessage(int recruit_id);
}