package cn.tentact.nebula.shiro

import cn.tentact.nebula.web.ResponseBean
import com.google.gson.Gson
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Jwt过滤器
 */
class JwtFilter extends BasicHttpAuthenticationFilter {
	/**
	 * 判断用户是否想要登入。
	 * 检测header里面是否包含Authorization字段即可
	 */
	override protected isLoginAttempt(ServletRequest request, ServletResponse response) {
		var req = request as HttpServletRequest;
		var authorization = req.getHeader("Authorization");
		return authorization !== null;
	}

	/**
	 * 执行登录判断
	 */
	override executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		var req = request as HttpServletRequest;
		var authorization = req.getHeader("Authorization");

		var token = new JwtToken(authorization);
		// 提交给realm进行登入，如果错误他会抛出异常并被捕获
		getSubject(request, response).login(token);
		// 如果没有抛出异常则代表登入成功，返回true
		return true;
	}

	/**
	 * 这里我们详细说明下为什么最终返回的都是true，即允许访问
	 * 例如我们提供一个地址 GET /article
	 * 登入用户和游客看到的内容是不同的
	 * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
	 * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
	 * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
	 * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
	 */
	override isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if(isLoginAttempt(request, response)) {
			try {
				executeLogin(request, response);
			} catch(Exception e) {
				var resp = response as HttpServletResponse;
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json; charset=utf-8");
				var gson = new Gson();
				var json = gson.toJson(new ResponseBean(200, "not login", false, null));
				var output = resp.outputStream;
				output.write(json.bytes);
				output.flush
				output.close
//				resp.sendRedirect("/401");
			}
		}
		return true;
	}

	override preHandle(ServletRequest request, ServletResponse response) throws Exception {
		var req = request as HttpServletRequest;
		var resp = response as HttpServletResponse;
		resp.setHeader("Access-control-Allow-Origin", req.getHeader("Origin"));
		resp.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
		// 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
		resp.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
		if(req.getMethod().equals(RequestMethod.OPTIONS.name())) {
			resp.setStatus(HttpStatus.OK.value());
			return false;
		}
		return super.preHandle(request, response);
	}

}
