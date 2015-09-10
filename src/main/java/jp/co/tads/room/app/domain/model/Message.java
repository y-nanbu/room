package jp.co.tads.room.app.domain.model;

import jp.co.tads.room.annotation.Column;
import jp.co.tads.room.annotation.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * メッセージのモデルクラスです。
 *
 * @author TAS
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class Message {

    public static final String TABLE_NAME = "MESSAGE";

    public static final String ID = "ID";

    public static final String MESSAGE = "MESSAGE";

    public static final String USER_ID = "USER_ID";

    public static final String UPDATED_AT = "UPDATED_AT";

    public static final String CREATED_AT = "CREATED_AT";

    public static final String LAST_UPDATED = "LAST_UPDATED";

    @Id
    @Column("ID")
    private String id;

    @Column("MESSAGE")
    private String message;

    @Column("USER_ID")
    private String userId;

    @Column("UPDATED_AT")
    private Timestamp updatedAt;

    @Column("CREATED_AT")
    private Timestamp createdAt;

    @Column("LAST_UPDATED")
    private String lastUpdated;
}
