package cn.graydove.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author graydove
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paging<T> {

    Collection<T> data;

    private Long total;

    public static <T> Paging<T> of(Page<T> page) {
        return new Paging<>(page.getContent(), page.getTotalElements());
    }

    public static <R, T> Paging<R> of(Page<T> page, Function<Stream<T>, Collection<R>> function) {
        return new Paging<>(function.apply(page.get()), page.getTotalElements());
    }
}
