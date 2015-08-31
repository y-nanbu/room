package jp.co.tads.room.infra.jdbc;

public interface JdbcManager {

    <T> T findOne(Class<T> clazz, SqlBuilder sb);
}
