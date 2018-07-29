package cn.tentact.nebula.shiro

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator
import org.apache.shiro.mgt.DefaultSubjectDAO
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import java.util.HashMap
import org.springframework.context.annotation.DependsOn
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
import org.apache.shiro.spring.LifecycleBeanPostProcessor
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.core.annotation.Order
import org.springframework.beans.factory.annotation.Qualifier

/**
 * Shiro配置类
 */
@Configuration
@Order(20)
class ShiroConfig {
	@Bean(name="jwtRealm")
	def getAuthRealm() {
		var jwtRealm = new JwtRealm();
		return jwtRealm;
	}

	@Bean("securityManager")
	def getManager(@Qualifier("jwtRealm")JwtRealm jwtRealm) {
		var manager = new DefaultWebSecurityManager();
		// 使用自己的realm
		manager.setRealm(jwtRealm);

		/*
		 * 关闭shiro自带的session，详情见文档
		 */
		var subjectDAO = new DefaultSubjectDAO();
		var defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		manager.setSubjectDAO(subjectDAO);

		return manager;
	}

	@Bean("shiroFilter")
	def factory(DefaultWebSecurityManager securityManager) {
		var factoryBean = new ShiroFilterFactoryBean();

		// 添加自己的过滤器并且取名为jwt
		var filterMap = new HashMap();
		filterMap.put("jwt", new JwtFilter());
		factoryBean.setFilters(filterMap);

		factoryBean.setSecurityManager(securityManager);
		factoryBean.setUnauthorizedUrl("/401");

		/*
		 * 自定义url规则
		 */
		var filterRuleMap = new HashMap();
		// 所有请求通过我们自己的JWT Filter
		filterRuleMap.put("/**", "jwt");
		filterRuleMap.put("/user/login", "anon");
		filterRuleMap.put("/test/**", "anon");
		// 访问401和404页面不通过我们的Filter
		filterRuleMap.put("/401", "anon");
		factoryBean.setFilterChainDefinitionMap(filterRuleMap);
		return factoryBean;
	}

	/**
	 * 下面的代码是添加注解支持
	 */
	@Bean
	@ConditionalOnMissingBean
	@DependsOn("lifecycleBeanPostProcessor")
	def defaultAdvisorAutoProxyCreator() {
		var creator = new DefaultAdvisorAutoProxyCreator();
		creator.proxyTargetClass = true
		creator;
	}

	@Bean
	def lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	def authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		var advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

//	@Bean
//	def shiroFilter(SecurityManager securityManager) {
//		var shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//		shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//		// 登陆画面
//		//shiroFilterFactoryBean.loginUrl = SystemResource.peony + "/login.html";
//		// 登录成功页面
//		//shiroFilterFactoryBean.successUrl = SystemResource.peony;
//		// 未授权界面
//		//shiroFilterFactoryBean.unauthorizedUrl = SystemResource.peony + "/403.html";
//		// 拦截器
//		var map = new LinkedHashMap<String, String>();
//		// 配置不会被拦截的链接 顺序判断
////		map.put("/user/login", "anon");
//		// map.put("/test/**", "anon");
////		map.put("/user/isAuthorization", "anon");
//		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
////		map.put("/logout", "logout");
////		map.put("/website/news/add", "authc,perms[0,2-1]");
////		map.put("/website/news/search", "authc,perms[0,2-4]");
////		map.put("/website/news/search/count", "authc,perms[0,2-4]");
////		map.put("/website/news/search/info/list", "authc,perms[0,2-4]");
////		map.put("/website/news/remove", "authc,perms[0,2-2]");
////
////		map.put("/website/keyword/search/count", "authc,perms[0,4-4]");
////		map.put("/website/keyword/search/info/list", "authc,perms[0,4-4]");
////		map.put("/website/keyword/remove/id", "authc,perms[0,4-2]");
////		map.put("/website/keyword/remove/ids", "authc,perms[0,4-2]");
////		map.put("/website/keyword/add", "authc,perms[0,4-1]");
////		map.put("/website/keyword/apply", "authc,perms[0,4-3]");
////		map.put("/file/upload/image", "anon");
////		map.put("/**", "authc");
//
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//		logger.info("Shiro拦截器注入成功");
//
//		return shiroFilterFactoryBean;
//	}
//
//
//	@Bean
//	@ConditionalOnMissingBean
//	def defaultAdvisorAutoProxyCreator() {
//		var creator = new DefaultAdvisorAutoProxyCreator();
//		creator.proxyTargetClass = true
//		return creator;
//	}
//
//	@Bean
//	def authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//		var advisor = new AuthorizationAttributeSourceAdvisor();
//		advisor.securityManager = securityManager;
//		return advisor;
//	}
}
