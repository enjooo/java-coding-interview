package com.vftour.study.core.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Optional;

/**
 * 统一API响应结果封装
 *
 * @author 东东 | d@tke.store
 * @date 2022/6/30 16:55
 * @since 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认为空消息
     */
    private static String DEFAULT_NULL_MESSAGE = "暂无承载数据";

    /**
     * 默认成功消息
     */
    private static String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    /**
     * 默认失败消息
     */
    private static String DEFAULT_FAILURE_MESSAGE = "操作失败";
    /**
     * 默认未授权消息
     */
    private static String DEFAULT_UNAUTHORIZED_MESSAGE = "签名认证失败";
    /**
     * "状态码"
     */
    private int code;
    /**
     * "是否成功"
     */
    private boolean success;
    /**
     * "承载数据"
     */
    private T data;
    /**
     * "返回消息"
     */
    private String message;

    private Result(ResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private Result(ResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private Result(ResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private Result(ResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.message = msg;
        this.success = ResultCode.SUCCESS.code == code;
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isSuccess(Result<?> result) {
        return Optional.ofNullable(result)
                .map(x -> ObjectUtils.nullSafeEquals(ResultCode.SUCCESS.code, x.code))
                .orElse(Boolean.FALSE);
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isNotSuccess(Result<?> result) {
        return !Result.isSuccess(result);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> data(T data) {
        return data(data, DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> data(T data, String msg) {
        return data(HttpServletResponse.SC_OK, data, msg);
    }

    /**
     * 返回R
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> data(int code, T data, String msg) {
        return new Result<>(code, data, data == null ? DEFAULT_NULL_MESSAGE : msg);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return Result
     */
    public static <T> Result<T> success(String msg) {
        return new Result<>(ResultCode.SUCCESS, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return Result
     */
    public static <T> Result<T> success(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return Result
     */
    public static <T> Result<T> success(ResultCode resultCode, String msg) {
        return new Result<>(resultCode, msg);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return Result
     */
    public static <T> Result<T> fail(String msg) {
        return new Result<>(ResultCode.INTERNAL_SERVER_ERROR, msg);
    }


    /**
     * 返回R
     *
     * @param code 状态码
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, null, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return Result
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return Result
     */
    public static <T> Result<T> fail(ResultCode resultCode, String msg) {
        return new Result<>(resultCode, msg);
    }

    /**
     * 返回R
     *
     * @param flag 成功状态
     * @return Result
     */
    public static <T> Result<T> status(boolean flag) {
        return flag ? success(DEFAULT_SUCCESS_MESSAGE) : fail(DEFAULT_FAILURE_MESSAGE);
    }

}
