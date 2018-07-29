package cn.tentact.nebula.web

/**
 * 自定义未授权异常
 */
public class UnauthorizedException extends RuntimeException {
	new(String msg) {
		super(msg);
	}

	new() {
		super();
	}
}
