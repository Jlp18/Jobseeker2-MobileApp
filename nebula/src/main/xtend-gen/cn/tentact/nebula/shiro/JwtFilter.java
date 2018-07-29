package cn.tentact.nebula.shiro;

import cn.tentact.nebula.shiro.JwtToken;
import cn.tentact.nebula.web.ResponseBean;
import com.google.gson.Gson;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Jwt过滤器
 */
@SuppressWarnings("all")
public class JwtFilter extends BasicHttpAuthenticationFilter {
  /**
   * 判断用户是否想要登入。
   * 检测header里面是否包含Authorization字段即可
   */
  @Override
  protected boolean isLoginAttempt(final ServletRequest request, final ServletResponse response) {
    HttpServletRequest req = ((HttpServletRequest) request);
    String authorization = req.getHeader("Authorization");
    return (authorization != null);
  }
  
  /**
   * 执行登录判断
   */
  @Override
  public boolean executeLogin(final ServletRequest request, final ServletResponse response) throws Exception {
    HttpServletRequest req = ((HttpServletRequest) request);
    String authorization = req.getHeader("Authorization");
    JwtToken token = new JwtToken(authorization);
    this.getSubject(request, response).login(token);
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
  @Override
  public boolean isAccessAllowed(final ServletRequest request, final ServletResponse response, final Object mappedValue) {
    try {
      boolean _isLoginAttempt = this.isLoginAttempt(request, response);
      if (_isLoginAttempt) {
        try {
          this.executeLogin(request, response);
        } catch (final Throwable _t) {
          if (_t instanceof Exception) {
            final Exception e = (Exception)_t;
            HttpServletResponse resp = ((HttpServletResponse) response);
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json; charset=utf-8");
            Gson gson = new Gson();
            ResponseBean _responseBean = new ResponseBean(200, "not login", false, null);
            String json = gson.toJson(_responseBean);
            ServletOutputStream output = resp.getOutputStream();
            output.write(json.getBytes());
            output.flush();
            output.close();
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
      }
      return true;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  public boolean preHandle(final ServletRequest request, final ServletResponse response) throws Exception {
    HttpServletRequest req = ((HttpServletRequest) request);
    HttpServletResponse resp = ((HttpServletResponse) response);
    resp.setHeader("Access-control-Allow-Origin", req.getHeader("Origin"));
    resp.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
    resp.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
    boolean _equals = req.getMethod().equals(RequestMethod.OPTIONS.name());
    if (_equals) {
      resp.setStatus(HttpStatus.OK.value());
      return false;
    }
    return super.preHandle(request, response);
  }
}
