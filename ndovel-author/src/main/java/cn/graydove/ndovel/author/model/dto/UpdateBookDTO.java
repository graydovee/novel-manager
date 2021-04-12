package cn.graydove.ndovel.author.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class UpdateBookDTO implements Serializable {

    private static final long serialVersionUID = -5508933007440278235L;

    private Long id;

    private String name;

    private String introduce;
}
