package cn.tentact.nebula.db.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Ajax跨域配置类
 */
@Configuration
@SuppressWarnings("all")
public class CorsConfig extends WebMvcConfigurerAdapter {
  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("GET", "POST", "DELETE", 
      "PUT").maxAge(3600);
  }
}
