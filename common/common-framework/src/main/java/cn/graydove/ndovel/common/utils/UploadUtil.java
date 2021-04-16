package cn.graydove.ndovel.common.utils;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author graydove
 */
public class UploadUtil {

    private static final String SIGN = "image/";

    private static final Map<String, String> SUFFIX_MAP;

    static {
        SUFFIX_MAP = new HashMap<>();
        SUFFIX_MAP.put("jpeg", "jpg");
    }

    public static String getImageSuffix(String contentType) {
        if (StrUtil.isBlank(contentType)) {
            return null;
        }
        if (contentType.length() <= SIGN.length() || !contentType.startsWith(SIGN)) {
            return null;
        }
        contentType = contentType.substring(SIGN.length()).toLowerCase();
        String suffix = SUFFIX_MAP.get(contentType);
        return suffix == null ? contentType : suffix;
    }
}
