package jp.co.tads.room.infra.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JdbcManagerImpl implements JdbcManager {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public <T> T findOne(Class<T> clazz, SqlBuilder sb) {
        RowMapper<T> mapper = new BeanPropertyRowMapper<>(clazz);
        try {
            return jdbcTemplate.queryForObject(sb.getQuery(), new MapSqlParameterSource(sb.getParams()), mapper);
        } catch (EmptyResultDataAccessException e) {
            if (e.getExpectedSize() == 1 && e.getActualSize() == 0) {
                return null;
            } else {
                throw e;
            }
        }
    }

    @Override
    public void insert(SqlBuilder sb) {
        jdbcTemplate.update(sb.getQuery(), sb.getParams());
    }
}
