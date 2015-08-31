package jp.co.tads.room.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.tads.room.exception.AppException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 様々なオブジェクトを生成するためのユーティリティクラスです。
 *
 * <p>
 *     このクラスでは，例外発生時に検査例外を発生させず，
 *     非検査例外である{@link jp.co.tads.room.exception.AppException AppException}を発生させます。
 *     これは，呼び出し側機能に余計な例外処理をさせないためです。
 *     呼び出し元で，このクラスが提供する機能が例外を発生させたときに何かの処理を行いたい場合は，
 *     AppExceptionをキャッチして処理してください。
 * </p>
 *
 * @author TAS
 */
public final class Factories {

    private Factories() {}

    /**
     * 指定されたのオブジェクトからJSON文字列を生成して返します。
     *
     * @param value 任意のオブジェクト
     * @param <E> 任意のオブジェクトの型
     * @return JSON文字列
     */
    public static <E> String createJsonString(E value) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new AppException(e);
        }
    }

    /**
     * 代入先の型に合わせて型変換を行います。
     *
     * @param o 任意のオブジェクト
     * @param <E> 任意の型
     * @return 任意の型
     */
    @SuppressWarnings("unchecked")
    public static <E> E cast(Object o) {
        return (E) o;
    }

    @SafeVarargs
    public static <E> List<E> list(E... objects) {
        List<E> list = new ArrayList<>();
        Collections.addAll(list, objects);
        return list;
    }
}
