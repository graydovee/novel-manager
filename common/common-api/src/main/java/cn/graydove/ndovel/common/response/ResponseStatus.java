package cn.graydove.ndovel.common.response;

/**
 * @author graydove
 */
public enum ResponseStatus {

    /**
     * 成功
     */
    OK(0, "success"),

    /**
     * 无权限
     */
    FORBIDDEN(21, "forbidden"),

    /**
     * 未登录
     */
    UNAUTHORIZED(22, "UNAUTHORIZED"),

    /**
     * 业务异常
     */
    TASK_ERROR(11, "task error"),

    /**
     * 参数校验失败
     */
    NOT_VALID(12, "parameter not valid"),

    /**
     * 请求失败
     */
    FAIL(13, "fail");

    private Integer code;

    private String desc;

    ResponseStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
