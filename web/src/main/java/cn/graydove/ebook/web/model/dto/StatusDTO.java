package cn.graydove.ebook.web.model.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Data
public class StatusDTO {
    private Integer code;
    private String info;
    private Object data;

    public StatusDTO() {
        this(HttpStatus.OK);
    }

    public StatusDTO(HttpStatus status) {
        code = status.value();
        info = status.getReasonPhrase();
    }

    public StatusDTO(String msg) {
        this();
        Map<String, String> map = new HashMap<>();
        map.put("msg", msg);
        this.data = map;
    }

    public StatusDTO(Object data) {
        this();
        this.data = data;
    }
}
