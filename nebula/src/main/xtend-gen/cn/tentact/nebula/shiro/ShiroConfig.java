package cn.tentact.nebula.shiro;

import cn.tentact.nebula.shiro.JwtFilter;
import cn.tentact.nebula.shiro.JwtRealm;
import java.util.HashMap;
import javax.servlet.Filter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

/**
 * Shiro配置类
 */
@Configuration
@Order(20)
@SuppressWarnings("all")
public class ShiroConfig {
  @Bean(name = "jwtRealm")
  public JwtRealm getAuthRealm() {
    JwtRealm jwtRealm = new JwtRealm();
    return jwtRealm;
  }
  
  @Bean("securityManager")
  public DefaultWebSecurityManager getManager(@Qualifier("jwtRealm") final JwtRealm jwtRealm) {
    DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
    manager.setRealm(jwtRealm);
    DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
    DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
    defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
    subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
    manager.setSubjectDAO(subjectDAO);
    return manager;
  }
  
  @Bean("shiroFilter")
  public ShiroFilterFactoryBean factory(final DefaultWebSecurityManager securityManager) {
    ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
    HashMap<String, Filter> filterMap = new HashMap<String, Filter>();
    JwtFilter _jwtFilter = new JwtFilter();
    filterMap.put("jwt", _jwtFilter);
    factoryBean.setFilters(filterMap);
    factoryBean.setSecurityManager(securityManager);
    factoryBean.setUnauthorizedUrl("/401");
    HashMap<String, String> filterRuleMap = new HashMap<String, String>();
    filterRuleMap.put("/**", "jwt");
    filterRuleMap.put("/user/login", "anon");
    filterRuleMap.put("/test/**", "anon");
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
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator _xblockexpression = null;
    {
      DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
      creator.setProxyTargetClass(true);
      _xblockexpression = creator;
    }
    return _xblockexpression;
  }
  
  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }
  
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(final DefaultWebSecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }
}
