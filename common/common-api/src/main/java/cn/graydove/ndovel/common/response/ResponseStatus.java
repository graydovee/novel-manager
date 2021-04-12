package cn.graydove.ndovel.common.response;

import lombok.Getter;

/**
 * @author graydove
 */
@Getter
public enum ResponseStatus {

    /**
     * 成功
     */
    OK(0, "success", "成功"),

    /**
     * 无权限
     */
    FORBIDDEN(21, "forbidden", "无权限"),

    /**
     * 未登录
     */
    UNAUTHORIZED(22, "unauthorized", "未登录"),

    /**
     * 获取登录用户信息异常
     */
    USER_ERROR(23, "user error", "获取登录用户信息异常"),

    /**
     * 业务异常
     */
    TASK_ERROR(11, "task error", "业务异常"),

    /**
     * 参数校验失败
     */
    NOT_VALID(12, "parameter not valid", "参数校验失败"),

    /**
     * 请求失败
     */
    FAIL(13, "fail", "请求失败");

    private Integer code;

    private String codeDesc;

    private String defaultMessage;

    ResponseStatus(Integer code, String codeDesc, String defaultMessage) {
        this.code = code;
        this.codeDesc = codeDesc;
        this.defaultMessage = defaultMessage;
    }

}
