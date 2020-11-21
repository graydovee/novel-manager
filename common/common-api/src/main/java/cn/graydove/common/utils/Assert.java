package cn.graydove.common.utils;

import cn.graydove.common.exception.TaskException;

public class Assert {
    public static void NotNull(Object o) {
        if (o == null) {
            throw new TaskException("pram is null");
        }
    }
}
