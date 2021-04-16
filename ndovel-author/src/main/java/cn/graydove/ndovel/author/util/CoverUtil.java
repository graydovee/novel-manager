package cn.graydove.ndovel.author.util;

/**
 * @author graydove
 */
public class CoverUtil {

    public static String toCoverUri(Long bookId) {
        return "cover/cover_" + bookId;
    }
}
