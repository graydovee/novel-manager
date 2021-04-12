package cn.graydove.ndovel.author.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author graydove
 */
@Data
public class BookDTO implements Serializable {

    private static final long serialVersionUID = 1144306138756131451L;

    @NotBlank
    private String name;

    @NotBlank
    private String introduce;

    private String cover;

    @NotNull
    private Set<String> category;
}
