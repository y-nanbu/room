package jp.co.tads.room.infra.jdbc;

import java.util.List;

/**
 * データベースに対する操作機能を提供するインターフェースです。
 *
 * @author TAS
 */
public interface JdbcManager {

    /**
     * 検索結果を一件のみ取得します。
     * 結果が存在しない場合はNULLを返します。
     *
     * @param clazz 返り値として結果がマッピングされるクラス
     * @param sb SqlBuilderでSQLが構築されたSqlBuilderオブジェクト
     * @param <T> モデルクラスの型
     * @return 単一の検索結果
     *
     * @see jp.co.tads.room.infra.jdbc.SqlBuilder
     */
    <T> T findOne(Class<T> clazz, SqlBuilder sb);


    <T> List<T> findList(Class<T> clazz, SqlBuilder sb);

    /**
     * SqlBuilderにより構築されたSQLを発行します。
     *
     * @param sb SqlBuilderでSQLが構築されたSqlBuilderオブジェクト
     */
    void insert(SqlBuilder sb);
}
