package cn.graydove.common.advice;


import cn.graydove.common.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({Exception.class})
    public Response<Object> handleException(Exception e){
        return Response.fail(e.getMessage());
    }

}
