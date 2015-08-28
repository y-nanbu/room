package jp.co.tads.room.infra.jdbc;

import jp.co.tads.room.annotation.Column;
import jp.co.tads.room.annotation.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@Slf4j
public class JdbcManagerImpl implements JdbcManager {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public <T> T findOne(Class<T> clazz, Map<String, Object> params) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        Field[] fields = clazz.getDeclaredFields();
        int i = 0;
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                if (i != 0) {
                    sql.append(", ");
                }
                sql.append(column.value());
                i++;
            }
        }
        Table table = clazz.getAnnotation(Table.class);
        sql.append(" FROM ").append(table.value()).append(" WHERE ");

        int j = 0;
        for(Map.Entry<String, Object> e : params.entrySet()) {
            if (j != 0) {
                sql.append(" AND ");
            }
            sql.append(e.getKey()).append(" = :").append(e.getKey());
            j++;
        }

        RowMapper<T> mapper = new BeanPropertyRowMapper<>(clazz);
        try {
            return jdbcTemplate.queryForObject(sql.toString(), new MapSqlParameterSource(params), mapper);
        } catch (EmptyResultDataAccessException e) {
            if (e.getExpectedSize() == 1 && e.getActualSize() == 0) {
                return null;
            } else {
                throw e;
            }
        }
    }
}
