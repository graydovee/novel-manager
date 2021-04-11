package cn.graydove.ndovel.client;

import cn.graydove.ndovel.common.Singleton;
import cn.graydove.ndovel.user.api.domain.vo.UserVO;
import cn.hutool.system.UserInfo;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author graydove
 */
public class UserContext {

    private static ThreadLocal<Singleton<UserVO>> userVOThreadLocal = new ThreadLocal<>();

    public static UserVO getUser() {
        Singleton<UserVO> userGetter = userVOThreadLocal.get();
        if (null != userGetter) {
            return userGetter.get();
        }
        return null;
    }

    public static void remove() {
        userVOThreadLocal.remove();
    }

    public static void set(Supplier<UserVO> supplier) {
        userVOThreadLocal.set(new Singleton<>(supplier));
    }

}
