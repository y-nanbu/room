package jp.co.tads.room.infra.jdbc;

import jp.co.tads.room.common.Factories;

import java.util.*;

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

    public static SqlBuilder into(String tableName) {
        SqlBuilder sb = new SqlBuilder();
        sb.addInsert(tableName);
        return sb;
    }

    private void columns(List<String> columns) {
        this.query
                .append("(")
                .append(String.join(", ", columns))
                .append(")");
    }

    public SqlBuilder values(Map<String, Object> values) {
        List<String> valuesList = new LinkedList<>();
        values.forEach((k, v) -> valuesList.add(k));
        columns(valuesList);

        this.query.append(" VALUES (");
        int i = 0;
        for(String valueName : valuesList) {
            if (i != 0) {
                this.query.append(", ");
            }
            this.query.append(":").append(valueName);
            i++;
        }
        this.params.putAll(values);
        this.query.append(")");
        return this;
    }


    public SqlBuilder from(String tableName) {
        this.query
                .append(" FROM ")
                .append(tableName);
        return this;
    }

    public SqlBuilder where() {
        this.query.append(" WHERE ");
        return this;
    }

    public SqlBuilder and() {
        this.query.append(" AND ");
        return this;
    }

    public SqlBuilder eq(String column, Object value) {
        this.query
                .append(column)
                .append( "= :")
                .append(column);
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
        this.query
                .append("SELECT ")
                .append(String.join(", ", columns));
    }

    private void addInsert(String tableName) {
        this.query
                .append("INSERT INTO ")
                .append(tableName)
                .append(" ");
    }
}
