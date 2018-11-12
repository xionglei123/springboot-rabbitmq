package org.yh.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class WebLogAspect {
	private static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	// 1) execution(): 表达式主体
	// 2) 第一个public *号：表示返回类型， *号表示所有的类型。
	// 3) 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.king.controller包、子孙包下所有类的方法。
	// 4) 第二个*号：表示类名，*号表示所有的类。
	// 5) *(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
	@Pointcut("execution(public * org.yh.controller..*.*(..))")
	public void webLog() {

	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinpoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		log.info("请求地址URL:" + request.getRequestURL().toString());
		log.info("请求方式HTTP_METHOD:" + request.getMethod());
		log.info("请求IP:" + request.getRemoteAddr());
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = enu.nextElement();
			log.info("参数名name:{},值value:{}", name, request.getParameter(name));
		}
	}

	@AfterReturning(returning = "obj", pointcut = "webLog()")
	public void doAfterReturning(Object obj) throws Throwable {
		log.info("耗时COST Time:" + (System.currentTimeMillis() - startTime.get())+"ms");
		log.info("返回结果RESPONSE:" + obj);
	}
}
