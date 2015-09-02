package jp.co.tads.room.common;

/**
 * 文字列を扱う機能を提供するユーティリティクラスです。
 *
 * @author TAS
 */
public final class StringUtil {

    private StringUtil() {}

    /**
     * 指定の要素すべてが{@link java.lang.String#startsWith(String)
     * String#startsWith}の条件に一致する場合，TRUEを返します。
     *
     * @param s 検査元文字列
     * @param target 検査対象文字列
     * @return 真偽値
     */
    public static boolean isAllStartWith(String s, String... target) {
        for (String t : target) {
            if (!s.startsWith(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 指定の要素の1つでも{@link java.lang.String#startsWith(String)
     * String#startsWith}の条件に一致する場合，TRUEを返します。
     *
     * @param s 検査元文字列
     * @param target 検査対象文字列
     * @return 真偽値
     */
    public static boolean isAnyStartWith(String s, String... target) {
        for (String t : target) {
            if (s.startsWith(t)) return true;
        }
        return false;
    }
}
