package cn.graydove.common.response;

public enum ResponseStatus {
    OK(0, "success"),
    TASK_ERROR(1, "task error"),
    NOT_VALID(2, "parameter not valid"),
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
