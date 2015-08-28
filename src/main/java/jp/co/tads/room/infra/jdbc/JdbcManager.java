package jp.co.tads.room.infra.jdbc;

import java.util.Map;

public interface JdbcManager {

    <T> T findOne(Class<T> clazz, Map<String, Object> params);
}
