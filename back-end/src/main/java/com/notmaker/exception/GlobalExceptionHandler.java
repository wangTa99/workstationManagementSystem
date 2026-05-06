package com.notmaker.exception;

import com.notmaker.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * 统一处理系统中抛出的各类异常，返回统一的错误响应格式
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     * @param e 业务异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e, HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回业务异常响应
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数校验异常
     * @param e 参数校验异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(ParamException.class)
    public Result<?> handleParamException(ParamException e, HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回参数异常响应
        return Result.error(400, e.getMessage());
    }

    /**
     * 处理认证异常
     * @param e 认证异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(AuthException.class)
    public Result<?> handleAuthException(AuthException e, HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回认证异常响应
        return Result.error(401, e.getMessage());
    }

    /**
     * 处理权限异常
     * @param e 权限异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(PermissionException.class)
    public Result<?> handlePermissionException(PermissionException e, HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回权限异常响应
        return Result.error(403, e.getMessage());
    }

    /**
     * 处理资源不存在异常
     * @param e 资源不存在异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public Result<?> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回资源不存在异常响应
        return Result.error(404, e.getMessage());
    }

    /**
     * 处理SQL异常
     * @param e SQL异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public Result<?> handleDataIntegrityViolationException(
            org.springframework.dao.DataIntegrityViolationException e,
            HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回数据完整性异常响应
        return Result.error(500, "数据操作异常，请检查数据格式");
    }

    /**
     * 处理数据库记录不存在异常
     * @param e 数据库记录不存在异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
    public Result<?> handleEmptyResultDataAccessException(
            org.springframework.dao.EmptyResultDataAccessException e,
            HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回记录不存在异常响应
        return Result.error(404, "数据记录不存在");
    }

    /**
     * 处理JSON解析异常
     * @param e JSON解析异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(com.fasterxml.jackson.core.JsonProcessingException.class)
    public Result<?> handleJsonProcessingException(
            com.fasterxml.jackson.core.JsonProcessingException e,
            HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回JSON解析异常响应
        return Result.error(400, "数据格式错误，请检查JSON格式");
    }

    /**
     * 处理运行时异常
     * @param e 运行时异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(NullPointerException.class)
    public Result<?> handleNullPointerException(NullPointerException e, HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回空指针异常响应
        return Result.error(500, "系统错误，数据处理异常");
    }

    /**
     * 处理数组越界异常
     * @param e 数组越界异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public Result<?> handleArrayIndexOutOfBoundsException(
            ArrayIndexOutOfBoundsException e,
            HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回数组越界异常响应
        return Result.error(500, "系统错误，数据访问异常");
    }

    /**
     * 处理类型转换异常
     * @param e 类型转换异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(ClassCastException.class)
    public Result<?> handleClassCastException(ClassCastException e, HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回类型转换异常响应
        return Result.error(500, "系统错误，类型转换异常");
    }

    /**
     * 处理算术运算异常
     * @param e 算术运算异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(ArithmeticException.class)
    public Result<?> handleArithmeticException(ArithmeticException e, HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回算术运算异常响应
        return Result.error(500, "系统错误，算术运算异常");
    }

    /**
     * 处理文件上传异常
     * @param e 文件上传异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(org.springframework.web.multipart.MaxUploadSizeExceededException.class)
    public Result<?> handleMaxUploadSizeExceededException(
            org.springframework.web.multipart.MaxUploadSizeExceededException e,
            HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回文件上传异常响应
        return Result.error(400, "文件大小超出限制");
    }

    /**
     * 处理文件解析异常
     * @param e 文件解析异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(org.springframework.web.multipart.MaxUploadSizeExceededException.class)
    public Result<?> handleMultipartException(
            org.springframework.web.multipart.MultipartException e,
            HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回文件解析异常响应
        return Result.error(400, "文件解析异常");
    }

    /**
     * 处理方法参数校验异常
     * @param e 方法参数校验异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(
            org.springframework.web.bind.MethodArgumentNotValidException e,
            HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 获取第一个校验错误信息
        String message = e.getBindingResult().getFieldError() != null
                ? e.getBindingResult().getFieldError().getDefaultMessage()
                : "参数校验失败";
        // 返回方法参数校验异常响应
        return Result.error(400, message);
    }

    /**
     * 处理绑定异常
     * @param e 绑定异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(org.springframework.web.bind.annotation.MethodArgumentTypeMismatchException.class)
    public Result<?> handleMethodArgumentTypeMismatchException(
            org.springframework.web.bind.annotation.MethodArgumentTypeMismatchException e,
            HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回绑定异常响应
        return Result.error(400, "参数类型不匹配");
    }

    /**
     * 处理缺失请求参数异常
     * @param e 缺失请求参数异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(org.springframework.web.bind.MissingServletRequestParameterException.class)
    public Result<?> handleMissingServletRequestParameterException(
            org.springframework.web.bind.MissingServletRequestParameterException e,
            HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回缺失请求参数异常响应
        return Result.error(400, "缺少必需参数：" + e.getParameterName());
    }

    /**
     * 处理HTTP请求方法不支持异常
     * @param e HTTP请求方法不支持异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public Result<?> handleHttpRequestMethodNotSupportedException(
            org.springframework.web.HttpRequestMethodNotSupportedException e,
            HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回HTTP请求方法不支持异常响应
        return Result.error(405, "请求方法不支持");
    }

    /**
     * 处理HTTP媒体类型不支持异常
     * @param e HTTP媒体类型不支持异常
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(org.springframework.web.HttpMediaTypeNotSupportedException.class)
    public Result<?> handleHttpMediaTypeNotSupportedException(
            org.springframework.web.HttpMediaTypeNotSupportedException e,
            HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回HTTP媒体类型不支持异常响应
        return Result.error(415, "请求媒体类型不支持");
    }

    /**
     * 处理所有未捕获的异常
     * @param e 异常对象
     * @param request HTTP请求对象
     * @return 统一响应结果
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e, HttpServletRequest request) {
        // 记录异常日志
        logException(e, request);
        // 返回系统异常响应
        return Result.error(500, "系统异常，请稍后再试");
    }

    /**
     * 记录异常日志的私有方法
     * @param e 异常对象
     * @param request HTTP请求对象
     */
    private void logException(Exception e, HttpServletRequest request) {
        // 获取请求URL
        String requestUrl = request.getRequestURL().toString();
        // 获取请求方法
        String requestMethod = request.getMethod();
        // 获取用户IP地址
        String ip = getClientIp(request);
        // 打印异常堆栈信息（实际项目中应使用日志框架如Logback）
        e.printStackTrace();
    }

    /**
     * 获取客户端真实IP地址
     * @param request HTTP请求对象
     * @return 客户端IP地址
     */
    private String getClientIp(HttpServletRequest request) {
        // 优先从代理头获取
        String ip = request.getHeader("X-Forwarded-For");
        // 如果为空则从代理头获取
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        // 如果为空则从WL代理头获取
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        // 如果为空则从HTTP代理头获取
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        // 如果为空则从HTTP代理头获取
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果为空则直接从请求获取
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 返回IP地址
        return ip;
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
