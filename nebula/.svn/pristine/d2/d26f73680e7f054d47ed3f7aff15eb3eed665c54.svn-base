package cn.tentact.nebula.web

import javax.servlet.http.HttpServletRequest
import org.apache.shiro.ShiroException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * 自定义异常控制器
 */
@RestControllerAdvice
class ExceptionController {

	/**
	 * 捕捉Shiro异常，返回状态码为401
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(ShiroException)
	def handle401(ShiroException e) {
		return new ResponseBean(401, e.getMessage(), false, null);
	}

	/**
	 * 捕捉未授权异常，返回状态码为401
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException)
	def handle401() {
		return new ResponseBean(401, "Unauthorized", false, null);
	}

	/**
	 * 捕捉其他异常，返回状态码500
	 */
	@ExceptionHandler(Exception)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	def globalException(HttpServletRequest request, Throwable ex) {
		ex.printStackTrace
		return new ResponseBean(getStatus(request).value(), ex.getMessage(), false, null);
	}

	def getStatus(HttpServletRequest request) {
		var statusCode = request.getAttribute("javax.servlet.error.status_code") as Integer;
		if(statusCode === null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}
}
