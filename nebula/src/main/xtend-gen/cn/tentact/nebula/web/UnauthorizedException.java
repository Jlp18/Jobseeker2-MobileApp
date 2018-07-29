package cn.tentact.nebula.web;

/**
 * 自定义未授权异常
 */
@SuppressWarnings("all")
public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(final String msg) {
    super(msg);
  }
  
  public UnauthorizedException() {
    super();
  }
}
