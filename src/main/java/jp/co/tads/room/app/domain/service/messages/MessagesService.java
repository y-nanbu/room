package jp.co.tads.room.app.domain.service.messages;

import static jp.co.tads.room.infra.jdbc.SqlBuilder.*;
import static jp.co.tads.room.common.Factories.*;

import jp.co.tads.room.app.domain.model.Message;
import jp.co.tads.room.app.domain.model.MessageJson;
import jp.co.tads.room.app.domain.model.User;
import jp.co.tads.room.app.domain.service.ServiceBase;
import jp.co.tads.room.infra.jdbc.SqlBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
     * @param loginUser ログインユーザ
     */
    public void addMessage(String message, User loginUser) {
        Map<String, Object> values = map();
        values.put(Message.MESSAGE, message);
        values.put(Message.USER_NAME, loginUser.getName());
        values.put(Message.UPDATED_AT, systimestamp());
        values.put(Message.CREATED_AT, systimestamp());
        values.put(Message.LAST_UPDATED, loginUser.getId());
        jdbcManager.insert(into(Message.TABLE_NAME).values(values));
    }

    public List<MessageJson> findMessageList(String timestamp) {
        SqlBuilder sb = select(
                10,
                Message.MESSAGE,
                Message.USER_NAME,
                Message.UPDATED_AT,
                Message.CREATED_AT,
                Message.LAST_UPDATED
        ).from(
                Message.TABLE_NAME
        );
        if (!timestamp.equals("")) {
            sb.where().lt(Message.UPDATED_AT, timestamp);
        }
        sb.desc(Message.UPDATED_AT);
        return jdbcManager.findList(MessageJson.class, sb);
    }
}
