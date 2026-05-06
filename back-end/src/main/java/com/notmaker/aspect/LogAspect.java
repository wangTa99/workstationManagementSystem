package com.notmaker.aspect;

import com.notmaker.entity.Log;
import com.notmaker.mapper.LogMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 日志切面类
 * 用于自动记录控制器的操作日志
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 日志Mapper
     */
    @Autowired
    private LogMapper logMapper;

    /**
     * 定义切点：com.notmaker.controller包下的所有类的所有方法
     */
    @Pointcut("execution(* com.notmaker.controller..*.*(..))")
    public void controllerPointcut() {
    }

    /**
     * 环绕通知：记录控制器的操作日志
     * @param joinPoint 连接点
     * @return 方法执行结果
     * @throws Throwable 方法执行抛出的异常
     */
    @Around("controllerPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 执行结果
        Object result = null;
        // 状态：1-成功，0-失败
        Integer status = 1;
        // 错误信息
        String errorMsg = null;

        try {
            // 执行目标方法
            result = joinPoint.proceed();
            return result;
        } catch (Throwable e) {
            // 设置状态为失败
            status = 0;
            // 设置错误信息
            errorMsg = e.getMessage();
            // 重新抛出异常
            throw e;
        } finally {
            // 结束时间
            long endTime = System.currentTimeMillis();
            // 执行时长
            long executionTime = endTime - startTime;

            try {
                // 保存日志
                saveLog(joinPoint, result, status, errorMsg, executionTime);
            } catch (Exception e) {
                // 日志保存失败不影响业务
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存日志
     * @param joinPoint 连接点
     * @param result 执行结果
     * @param status 执行状态
     * @param errorMsg 错误信息
     * @param executionTime 执行时长
     */
    private void saveLog(ProceedingJoinPoint joinPoint, Object result, Integer status, String errorMsg, long executionTime) {
        // 获取请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();

        // 创建日志对象
        Log log = new Log();

        // 设置请求信息
        log.setRequestUrl(request.getRequestURL().toString());
        log.setRequestMethod(request.getMethod());
        log.setRequestParam(getRequestParams(joinPoint));

        // 设置方法信息
        log.setModule(getModule(joinPoint));
        log.setMethod(joinPoint.getSignature().getName());

        // 设置用户信息（从session或token中获取，实际项目需要根据具体实现）
        log.setUserId(getUserId(request));
        log.setUserName(getUserName(request));

        // 设置客户端信息
        log.setIp(getClientIp(request));
        log.setUserAgent(request.getHeader("User-Agent"));

        // 设置执行结果
        log.setStatus(status);
        log.setExecutionTime(executionTime);
        log.setErrorMsg(errorMsg);

        // 设置操作类型
        log.setOperationType(getOperationType(joinPoint.getSignature().getName()));

        // 设置创建时间
        log.setCreateTime(LocalDateTime.now());

        // 保存日志
        logMapper.insert(log);
    }

    /**
     * 获取请求参数
     * @param joinPoint 连接点
     * @return 请求参数字符串
     */
    private String getRequestParams(ProceedingJoinPoint joinPoint) {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取参数名和参数值
        String[] paramNames = signature.getParameterNames();
        Object[] paramValues = joinPoint.getArgs();

        if (paramNames == null || paramValues == null) {
            return "";
        }

        // 拼接参数
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paramNames.length; i++) {
            if (i > 0) {
                sb.append("&");
            }
            sb.append(paramNames[i]).append("=").append(paramValues[i]);
        }

        return sb.toString();
    }

    /**
     * 获取模块名
     * @param joinPoint 连接点
     * @return 模块名
     */
    private String getModule(ProceedingJoinPoint joinPoint) {
        // 获取目标类的简单类名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        // 去掉Controller后缀
        if (className.endsWith("Controller")) {
            className = className.substring(0, className.length() - 10);
        }
        return className;
    }

    /**
     * 获取用户ID
     * @param request HTTP请求对象
     * @return 用户ID
     */
    private Long getUserId(HttpServletRequest request) {
        // 从请求属性、session或token中获取用户ID
        // 这里简单返回null，实际项目需要根据具体实现
        Object userId = request.getAttribute("userId");
        if (userId != null) {
            return (Long) userId;
        }
        return null;
    }

    /**
     * 获取用户名
     * @param request HTTP请求对象
     * @return 用户名
     */
    private String getUserName(HttpServletRequest request) {
        // 从请求属性、session或token中获取用户名
        // 这里简单返回null，实际项目需要根据具体实现
        Object userName = request.getAttribute("userName");
        if (userName != null) {
            return (String) userName;
        }
        return null;
    }

    /**
     * 获取客户端IP地址
     * @param request HTTP请求对象
     * @return IP地址
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 根据方法名获取操作类型
     * @param methodName 方法名
     * @return 操作类型码
     */
    private Integer getOperationType(String methodName) {
        // 根据方法名推断操作类型
        if (methodName.startsWith("add") || methodName.startsWith("insert") || methodName.startsWith("save") || methodName.startsWith("create")) {
            return 3; // 新增
        } else if (methodName.startsWith("update") || methodName.startsWith("edit") || methodName.startsWith("modify")) {
            return 4; // 修改
        } else if (methodName.startsWith("delete") || methodName.startsWith("remove")) {
            return 5; // 删除
        } else if (methodName.startsWith("get") || methodName.startsWith("find") || methodName.startsWith("query") || methodName.startsWith("list") || methodName.startsWith("page")) {
            return 6; // 查询
        } else if (methodName.startsWith("login")) {
            return 1; // 登录
        } else if (methodName.startsWith("logout")) {
            return 2; // 登出
        } else if (methodName.startsWith("export")) {
            return 7; // 导出
        } else if (methodName.startsWith("import")) {
            return 8; // 导入
        } else if (methodName.startsWith("upload")) {
            return 9; // 上传
        } else if (methodName.startsWith("download")) {
            return 10; // 下载
        } else {
            return 6; // 默认查询
        }
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
