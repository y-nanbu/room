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
@Table("users")
public class User extends AbstractEntity {

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PASSWORD = "password";

    public static final String UPDATED_AT = "updated_at";

    public static final String CREATED_AT = "created_at";

    public static final String LAST_UPDATED = "last_updated";

    @Id
    @Column("id")
    private String id;

    @Column("name")
    private String name;

    @Column("password")
    private String password;

    @Column("updated_at")
    private Timestamp updatedAt;

    @Column("created_at")
    private Timestamp createdAt;

    @Column("last_updated")
    private String lastUpdated;
}
