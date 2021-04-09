package cn.graydove.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author graydove
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paging<T> {

    private Long total;

    List<T> data;
}
