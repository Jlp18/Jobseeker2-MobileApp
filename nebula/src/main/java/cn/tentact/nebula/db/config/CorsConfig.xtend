package cn.tentact.nebula.db.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Ajax跨域配置类
 */
@Configuration
class CorsConfig extends WebMvcConfigurerAdapter {
	override addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("GET", "POST", "DELETE",
			"PUT").maxAge(3600);
	}

}
