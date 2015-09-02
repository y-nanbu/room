package jp.co.tads.room.common;

/**
 * 文字列を扱う機能を提供するユーティリティクラスです。
 *
 * @author TAS
 */
public final class StringUtil {

    private StringUtil() {}

    public static boolean isAllStartWith(String s, String... target) {
        for (String t : target) {
            if (!s.startsWith(t)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnyStartWith(String s, String... target) {
        for (String t : target) {
            if (s.startsWith(t)) return true;
        }
        return false;
    }
}
