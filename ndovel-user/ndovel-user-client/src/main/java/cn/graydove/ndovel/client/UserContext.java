package cn.graydove.ndovel.client;

import cn.graydove.ndovel.client.exception.UserException;
import cn.graydove.ndovel.common.Singleton;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author graydove
 */
public class UserContext {

    private static ThreadLocal<Singleton<UserVO>> userVOThreadLocal = new ThreadLocal<>();

    @Nullable
    public static UserVO getUser() {
        Singleton<UserVO> userGetter = userVOThreadLocal.get();
        if (null != userGetter) {
            return userGetter.get();
        }
        return null;
    }

    @NotNull
    public static UserVO getUserOrEx() {
        return Optional.ofNullable(userVOThreadLocal.get())
                .map(Singleton::get)
                .orElseThrow(UserException::new);
    }

    @Nullable
    public static Long getUserId() {
        UserVO user = getUser();
        if (null != user) {
            return user.getId();
        }
        return null;
    }

    public static void remove() {
        userVOThreadLocal.remove();
    }

    public static void setUserGetter(Supplier<UserVO> supplier) {
        userVOThreadLocal.set(new Singleton<>(supplier));
    }

}
