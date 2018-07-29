package cn.tentact.nebula.db.aop;

import com.google.common.base.Objects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;
import org.springframework.stereotype.Component;

/**
 * 网络AOP类，拦截Controller所有方法，将空字符串转换成null
 */
@Component
@Aspect
@SuppressWarnings("all")
public class WebControllerAop {
  /**
   * 切点
   */
  @Pointcut("execution(* cn.tentact.nebula..*.*Controller.*(..))")
  public void executeService() {
  }
  
  /**
   * 环绕通知
   */
  @Around("executeService()")
  public Object hello(final ProceedingJoinPoint joinPoint) {
    try {
      Object _xblockexpression = null;
      {
        final Object[] args = joinPoint.getArgs();
        final Procedure2<Object, Integer> _function = (Object one, Integer index) -> {
          boolean _equals = Objects.equal(one, "");
          if (_equals) {
            args[(index).intValue()] = null;
          }
        };
        IterableExtensions.<Object>forEach(((Iterable<Object>)Conversions.doWrapArray(args)), _function);
        _xblockexpression = joinPoint.proceed(args);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
