package cn.graydove.ndovel.spider.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class RegisterDTO implements Serializable {

    private static final long serialVersionUID = -6067238093314856691L;

    @NotBlank
    private String token;

    @NotBlank
    private String uid;

    @NotBlank
    private String publicKey;

    @NotBlank
    private String publishAddress;
}
