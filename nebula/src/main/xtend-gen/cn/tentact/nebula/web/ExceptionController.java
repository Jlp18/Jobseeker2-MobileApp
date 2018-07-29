package cn.tentact.nebula.web;

import cn.tentact.nebula.web.ResponseBean;
import cn.tentact.nebula.web.UnauthorizedException;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常控制器
 */
@RestControllerAdvice
@SuppressWarnings("all")
public class ExceptionController {
  /**
   * 捕捉Shiro异常，返回状态码为401
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(ShiroException.class)
  public ResponseBean handle401(final ShiroException e) {
    String _message = e.getMessage();
    return new ResponseBean(401, _message, false, null);
  }
  
  /**
   * 捕捉未授权异常，返回状态码为401
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(UnauthorizedException.class)
  public ResponseBean handle401() {
    return new ResponseBean(401, "Unauthorized", false, null);
  }
  
  /**
   * 捕捉其他异常，返回状态码500
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseBean globalException(final HttpServletRequest request, final Throwable ex) {
    ex.printStackTrace();
    int _value = this.getStatus(request).value();
    String _message = ex.getMessage();
    return new ResponseBean(_value, _message, false, null);
  }
  
  public HttpStatus getStatus(final HttpServletRequest request) {
    Object _attribute = request.getAttribute("javax.servlet.error.status_code");
    Integer statusCode = ((Integer) _attribute);
    if ((statusCode == null)) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return HttpStatus.valueOf((statusCode).intValue());
  }
}
