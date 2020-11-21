package cn.graydove.common.advice;


import cn.graydove.common.exception.TaskException;
import cn.graydove.common.response.Response;
import cn.graydove.common.response.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    private ObjectMapper objectMapper;

    public ControllerExceptionAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler({Throwable.class})
    public Response<Object> handleException(Throwable e){
        if (e instanceof TaskException) {
            return Response.fail(ResponseStatus.TASK_ERROR, e.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            List<ErrorMessage> errorMessageList = new ArrayList<>();
            for (ObjectError error : exception.getBindingResult().getAllErrors()) {
                errorMessageList.add(new ErrorMessage(error));
            }
            String message = null;
            try {
                message = objectMapper.writeValueAsString(errorMessageList);
            } catch (JsonProcessingException ignored) { }
            return Response.notValid(message);
        }
        return Response.fail(e.getMessage());
    }


    @Data
    public static class ErrorMessage {
        private String field;
        private String message;
        private Object rejectedValue;

        ErrorMessage(ObjectError error) {
            this.field = Optional.ofNullable(getField(error, "getField")).orElse("unknown field");
            this.message = error.getDefaultMessage();
            this.rejectedValue =  Optional.ofNullable(getField(error, "getRejectedValue")).orElse("unknown value");
        }

        @JsonIgnore
        private String getField(ObjectError error, String getMethodName) {
            try {
                return error.getClass().getMethod(getMethodName).invoke(error).toString();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                return null;
            }
        }
    }

}
