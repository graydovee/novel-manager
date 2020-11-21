package cn.graydove.common.response;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -6001478825110879072L;

    private Integer code;

    private T result;

    private Boolean success;

    private String message;

    private String error;

    private Response(Integer code, T result, Boolean success, String message, String error) {
        this.code = code;
        this.result = result;
        this.success = success;
        this.message = message;
        this.error = error;
    }


    public static<R> Response<R> success(R data) {
        return new Response<>(ResponseStatus.OK.getCode(), data, true, ResponseStatus.OK.getMessage(), null);
    }

    public static<R> Response<R> fail() {
        return fail(ResponseStatus.FAIL, null);
    }

    public static<R> Response<R> fail(String message) {
        return fail(ResponseStatus.FAIL, message);
    }

    public static<R> Response<R> fail(@NotNull ResponseStatus status, String error) {
        return new Response<>(status.getCode(), null, false, status.getMessage(), error);
    }
}
