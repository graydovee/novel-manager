package cn.graydove.ndovel.framework.advice;

import cn.graydove.ndovel.api.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({Exception.class})
    public Response<Object> handleException(Exception e){
        return Response.fail(e.getMessage());
    }

}
