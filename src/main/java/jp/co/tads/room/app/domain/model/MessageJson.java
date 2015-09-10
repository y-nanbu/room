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
public class MessageJson {

    @Id
    @Column("ID")
    private String id;

    @Column("MESSAGE")
    private String message;

    @Column("USER_NAME")
    private String userName;

    @Column("UPDATED_AT")
    private String updatedAt;

    @Column("CREATED_AT")
    private String createdAt;

    @Column("LAST_UPDATED")
    private String lastUpdated;
}
