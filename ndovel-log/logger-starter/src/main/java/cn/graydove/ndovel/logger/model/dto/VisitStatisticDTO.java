package cn.graydove.ndovel.logger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
@NoArgsConstructor
public class VisitStatisticDTO implements Serializable {

    private static final long serialVersionUID = 3056372937271519802L;

    private Long bookId;

    private Long visit;

    public VisitStatisticDTO(Long bookId, Long visit) {
        this.bookId = bookId;
        this.visit = visit;
    }
}
