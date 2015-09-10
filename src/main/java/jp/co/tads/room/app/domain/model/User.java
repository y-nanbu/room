package jp.co.tads.room.app.domain.model;

import jp.co.tads.room.annotation.Column;
import jp.co.tads.room.annotation.Id;
import jp.co.tads.room.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * ユーザモデルです。
 *
 * @author TAS
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class User {

    public static final String TABLE_NAME = "USERS";

    public static final String ID = "ID";

    public static final String NAME = "NAME";

    public static final String PASSWORD = "PASSWORD";

    public static final String UPDATED_AT = "UPDATED_AT";

    public static final String CREATED_AT = "CREATED_AT";

    public static final String LAST_UPDATED = "LAST_UPDATED";

    @Id
    @Column("ID")
    private String id;

    @Column("NAME")
    private String name;

    @Column("PASSWORD")
    private String password;

    @Column("UPDATED_AT")
    private Timestamp updatedAt;

    @Column("CREATED_AT")
    private Timestamp createdAt;

    @Column("LAST_UPDATED")
    private String lastUpdated;
}
