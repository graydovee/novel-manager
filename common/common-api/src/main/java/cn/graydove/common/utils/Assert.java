package cn.graydove.common.utils;

import cn.graydove.common.exception.TaskException;

/**
 * @author graydove
 */
public class Assert {
    public static void notNull(Object o) {
        if (o == null) {
            throw new TaskException("pram is null");
        }
    }
}
