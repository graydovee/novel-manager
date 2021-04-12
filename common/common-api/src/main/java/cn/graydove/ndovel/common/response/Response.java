package cn.graydove.ndovel.common.response;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -6001478825110879072L;

    private Integer code;

    private String codeDesc;

    private Boolean success;

    private T result;

    private String message;

    public Response(Integer code, String codeDesc, Boolean success, T result, String message) {
        this.code = code;
        this.codeDesc = codeDesc;
        this.success = success;
        this.result = result;
        this.message = message;
    }

    public Response(ResponseStatus status, T result, String message) {
        this.success = status == ResponseStatus.OK;
        this.code = status.getCode();
        this.codeDesc = status.getCodeDesc();
        this.result = result;
        this.message = message;
    }

    public static<R> Response<R> success(R data) {
        return new Response<>(ResponseStatus.OK, data, ResponseStatus.OK.getDefaultMessage());
    }

    public static Response ok() {
        return success(null);
    }

    public static<R> Response<R> fail() {
        return fail(ResponseStatus.FAIL, null);
    }

    public static<R> Response<R> fail(String message) {
        return fail(ResponseStatus.FAIL, message);
    }

    public static<R> Response<R> fail(@NotNull ResponseStatus status, String message) {
        return new Response<>(status, null, message);
    }

    public static<R> Response<R> fail(@NotNull ResponseStatus status) {
        return new Response<>(status, null, status.getDefaultMessage());
    }
}
