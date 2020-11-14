package cn.graydove.ndovel.api.utils;

import cn.graydove.ndovel.api.exception.TaskException;

public class Assert {
    public static void NotNull(Object o) {
        if (o == null) {
            throw new TaskException("pram is null");
        }
    }
}
