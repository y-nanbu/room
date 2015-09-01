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

    protected SqlBuilder() {}

    public static SqlBuilder select(String... columnNames) {
        SqlBuilder sb = new SqlBuilder();
        List<String> columns = Factories.list(columnNames);
        sb.addSelect(columns);
        return sb;
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


    public Map<String, Object> getParams() {
        return params;
    }

    public String getQuery() {
        return query.toString();
    }

    private void addSelect(List<String> columns) {
        this.query.append("SELECT ");
        this.query.append(String.join(", ", columns));
    }
}
