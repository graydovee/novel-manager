package cn.graydove.ebook.web.utils;

import java.util.Optional;

public class OptionalUtils {
    private OptionalUtils(){
    }

    public static <T> T getValue(Optional<T> optional){
        T t = null;
        if(optional.isPresent())
            t = optional.get();
        return t;
    }
}
