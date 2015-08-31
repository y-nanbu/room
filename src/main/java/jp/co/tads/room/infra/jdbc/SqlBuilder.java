package jp.co.tads.room.infra.jdbc;

import jp.co.tads.room.common.Factories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQLビルダークラスです。
 */
public class SqlBuilder {

    private StringBuilder query = new StringBuilder();

    private Map<String, Object> params = new HashMap<>();

    private static final String SELECT = "SELECT";

    private static final String UPDATE = "UPDATE";

    private static final String INSERT = "INSERT";

    private static final String DELETE = "DELETE";

    private String sqlType = null;

    public static final String ALL = "*";

    protected SqlBuilder() {}

    public static SqlBuilder select() {
        SqlBuilder sb = new SqlBuilder();
        sb.setSqlType(SELECT);
        return sb;
    }

    public SqlBuilder columns(String... columnName) {
        List<String> columns = Factories.list(columnName);
        if (getSqlType().equals(SELECT)) {
            query.append(" ");
            query.append(String.join(", ", columns));
        }
        return this;
    }

    public SqlBuilder columnsAll() {
        query.append(" ");
        query.append(ALL);
        return this;
    }

    public SqlBuilder from(String tableName) {
        query.append(" FROM ");
        query.append(tableName);
        return this;
    }

    public SqlBuilder where() {
        query.append(" WHERE ");
        return this;
    }

    public SqlBuilder and() {
        query.append(" AND ");
        return this;
    }

    public SqlBuilder eq(String column, Object value) {
        query.append(column);
        query.append( "= :");
        query.append(column);
        params.put(column, value);
        return this;
    }

    private void append(String s) {
        this.query.append(s);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public String getSqlType() {
        return sqlType;
    }

    private void setSqlType(String sqlType) {
        this.sqlType = sqlType;
        append(sqlType);
    }

    public String getQuery() {
        return query.toString();
    }
}
