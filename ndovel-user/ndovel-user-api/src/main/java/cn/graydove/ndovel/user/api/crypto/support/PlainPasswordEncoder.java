package cn.graydove.ndovel.user.api.crypto.support;


import cn.graydove.ndovel.user.api.crypto.PasswordEncoder;
import cn.hutool.core.util.StrUtil;

/**
 * @author graydove
 */
public class PlainPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return StrUtil.equals(rawPassword, encodedPassword);
    }
}
