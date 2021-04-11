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
     * 业务异常
     */
    TASK_ERROR(1, "task error"),

    /**
     * 参数校验失败
     */
    NOT_VALID(2, "parameter not valid"),

    /**
     * 请求失败
     */
    FAIL(10, "fail");

    private Integer code;

    private String message;

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
