package jp.co.tads.room.app.domain.service.messages;

import static jp.co.tads.room.infra.jdbc.SqlBuilder.*;
import static jp.co.tads.room.common.Factories.*;

import jp.co.tads.room.app.domain.model.Message;
import jp.co.tads.room.app.domain.service.ServiceBase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * メッセージに関するサービスクラスです。
 *
 * @author TAS
 */
@Service
@Transactional
public class MessagesService extends ServiceBase {

    /**
     * メッセージを登録します。
     *
     * @param message メッセージ
     * @param userId ユーザID
     */
    public void addMessage(String message, String userId) {
        Map<String, Object> values = map();
        values.put(Message.MESSAGE, message);
        values.put(Message.USER_ID, userId);
        values.put(Message.UPDATED_AT, systimestamp());
        values.put(Message.CREATED_AT, systimestamp());
        values.put(Message.LAST_UPDATED, userId);
        jdbcManager.insert(into(Message.TABLE_NAME).values(values));
    }
}
