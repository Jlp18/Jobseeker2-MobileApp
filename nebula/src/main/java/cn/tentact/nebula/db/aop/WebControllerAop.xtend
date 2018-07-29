package cn.tentact.nebula.db.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

/**
 * 网络AOP类，拦截Controller所有方法，将空字符串转换成null
 */
@Component
@Aspect
class WebControllerAop {
	/**
	 * 切点
	 */
	@Pointcut("execution(* cn.tentact.nebula..*.*Controller.*(..))")
	def void executeService() {
	}

	/**
	 * 环绕通知
	 */
	@Around("executeService()")
	def hello(ProceedingJoinPoint joinPoint) {
		val args = joinPoint.args;
		args.forEach [ one, index |
			if(one == "") {
				args.set(index, null); // 把空字符转换成null
			}
		]
		joinPoint.proceed(args)
	}
}
