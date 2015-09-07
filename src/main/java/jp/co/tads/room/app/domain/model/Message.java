package jp.co.tads.room.app.domain.model;

import jp.co.tads.room.annotation.Column;
import jp.co.tads.room.annotation.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * メッセージのモデルクラスです。
 *
 * @author
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class Message {

    public static final String TABLE_NAME = "message";

    public static final String ID = "id";

    public static final String MESSAGE = "message";

    public static final String USER_ID = "user_id";

    public static final String UPDATED_AT = "updated_at";

    public static final String CREATED_AT = "created_at";

    public static final String LAST_UPDATED = "last_updated";

    @Id
    @Column("id")
    private String id;

    @Id
    @Column("message")
    private String message;

    @Column("user_id")
    private String userId;

    @Column("updated_at")
    private Timestamp updatedAt;

    @Column("created_at")
    private Timestamp createdAt;

    @Column("last_updated")
    private String lastUpdated;
}
