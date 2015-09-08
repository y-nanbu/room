package jp.co.tads.room.app.domain.service.messages

import jp.co.tads.room.app.domain.model.Message
import jp.co.tads.room.common.SpockBase
import jp.co.tads.room.infra.jdbc.SqlBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

class MessagesServiceSpec extends SpockBase  {

    @Autowired
    MessagesService messagesService;

    @Transactional
    @Rollback
    def "メッセージが1件登録できること"() {
        when:
        messagesService.addMessage "message", "9999999999999999"

        then:
        Message message = jdbcManager.findOne(Message.class,
                SqlBuilder.select(
                        Message.ID,
                        Message.MESSAGE,
                        Message.USER_ID,
                        Message.UPDATED_AT,
                        Message.CREATED_AT,
                        Message.LAST_UPDATED
                ).from(
                        Message.TABLE_NAME
                ).where()
                        .eq(Message.USER_ID, '9999999999999999'))

        message.userId == "9999999999999999"
        message.message == "message"
        message.updatedAt != null
        message.createdAt != null
        message.lastUpdated != null
    }
}
