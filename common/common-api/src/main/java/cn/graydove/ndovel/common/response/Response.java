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

    private String errorMsg;

    public Response(Integer code, String codeDesc, Boolean success, T result, String errorMsg) {
        this.code = code;
        this.codeDesc = codeDesc;
        this.success = success;
        this.result = result;
        this.errorMsg = errorMsg;
    }

    public Response(ResponseStatus status, T result, String errorMsg) {
        this.success = status == ResponseStatus.OK;
        this.code = status.getCode();
        this.codeDesc = status.getDesc();
        this.result = result;
        this.errorMsg = errorMsg;
    }

    public static<R> Response<R> success(R data) {
        return new Response<>(ResponseStatus.OK, data, null);
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

    public static<R> Response<R> fail(@NotNull ResponseStatus status, String errorMsg) {
        return new Response<>(status, null, errorMsg);
    }

    public static<R> Response<R> fail(@NotNull ResponseStatus status) {
        return new Response<>(status, null, null);
    }
}
