package com.tcbs.ha.haservice.config;

import com.tcbs.ha.haservice.common.CommonUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Pointcut("execution(* com.tcbs.ha.haservice.controller..*(..)) " +
      " || execution(* com.tcbs.ha.haservice.client..*(..)) ")
  public void applicationPointcut() {
    //just point cut
  }

//  @Pointcut("execution(* com.tcbs.ha.haservice.services..*(..)) ")
//  public void servicePointcut() {
//    //just point cut
//  }

  @Pointcut("!@annotation(com.tcbs.ha.haservice.config.annotations.NoLogging)" +
      " && !@target(com.tcbs.ha.haservice.config.annotations.NoLogging)")
  public void noLoggingPointcut() {
    //just point cut
  }

  @Around("applicationPointcut() && noLoggingPointcut()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();  //Get request object
    String ip = request.getRemoteAddr();     //Get the ip address of the request object
    if (log.isInfoEnabled()) {
      log.info("=== {} call Start: {}.{}() with argument[s] = {} ===", ip,
          joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(), getRequestJsonString(joinPoint.getArgs()));
    }
    Object result = joinPoint.proceed();
    if (log.isInfoEnabled()) {
      log.info("=== {} End: {}.{}() with result = {} ===",
          ip, joinPoint.getSignature().getDeclaringTypeName(),
          joinPoint.getSignature().getName(), getResponseJsonString(result));
    }
    return result;
  }

  private String getRequestJsonString(Object[] objs) {
    StringBuilder str = new StringBuilder();
    for (Object obj : objs) {
      String tmp = getObjectJsonString(obj);
      if (tmp != null) {
        str.append(tmp).append(", ");
      }
    }
    str.append("]");
    return str.toString();
  }

  private String getResponseJsonString(Object obj) {
    if (obj instanceof List) {
      return getListJsonString(obj);
    } else if (obj instanceof ResponseEntity) {
      ResponseEntity responseEntity = (ResponseEntity) obj;
      Object body = responseEntity.getBody();
      if (body instanceof List) {
        return getListJsonString(body);
      } else {
        return getObjectJsonString(body);
      }
    } else {
      return getObjectJsonString(obj);
    }
  }

  private String getObjectJsonString(Object obj) {
    try {
      return CommonUtils.getGSonConverter().toJson(obj);
    } catch (Exception e) {
      return null;
    }
  }

  private String getListJsonString(Object obj) {
    try {
      List tmp = (List) obj;
      return "size: " + tmp.size();
    } catch (Exception e) {
      return null;
    }
  }

//  @Around("servicePointcut() && noLoggingPointcut()")
//  public Object logAroundService(ProceedingJoinPoint joinPoint) throws Throwable {
//    if (log.isInfoEnabled()) {
//      log.info("=============== Start: {}.{}()  ===============", joinPoint.getSignature().getDeclaringTypeName(),
//          joinPoint.getSignature().getName());
//    }
//    Object result = joinPoint.proceed();
//    if (log.isInfoEnabled()) {
//      log.info("=============== End: {}.{}()  ===============", joinPoint.getSignature().getDeclaringTypeName(),
//          joinPoint.getSignature().getName());
//    }
//    return result;
//  }
}
