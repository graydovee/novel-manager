package cn.graydove.ndovel.common.utils;

import cn.graydove.ndovel.common.exception.AbstractNovelException;
import cn.graydove.ndovel.common.exception.TaskException;

import java.util.function.Supplier;

/**
 * @author graydove
 */
public class Assert {
    public static void notNull(Object o) {
        if (o == null) {
            throw new TaskException("pram is null");
        }
    }

    public static void assertTrue(Boolean b) {
        if (!b) {
            throw new TaskException("assert not true");
        }
    }

    public static void assertTrue(Boolean b, Supplier<? extends AbstractNovelException> supplier) {
        if (!b) {
            throw supplier.get();
        }
    }
}
