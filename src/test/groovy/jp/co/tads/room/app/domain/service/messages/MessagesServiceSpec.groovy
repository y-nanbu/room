package jp.co.tads.room.app.domain.service.messages

import jp.co.tads.room.app.domain.model.Message
import jp.co.tads.room.app.domain.model.User
import jp.co.tads.room.common.SpockBase
import jp.co.tads.room.infra.jdbc.SqlBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

class MessagesServiceSpec extends SpockBase  {

    @Autowired
    MessagesService service;

    @Transactional
    @Rollback
    def "メッセージが1件登録できること"() {
        setup:
        User user = new User();
        user.id = '9999999999999999'
        user.name = "name"


        when:
        service.addMessage "message", user

        then:
        Message message = jdbcManager.findOne(Message.class,
                SqlBuilder.select(
                        Message.ID,
                        Message.MESSAGE,
                        Message.USER_NAME,
                        Message.UPDATED_AT,
                        Message.CREATED_AT,
                        Message.LAST_UPDATED
                ).from(
                        Message.TABLE_NAME
                ).where()
                        .eq(Message.USER_NAME, 'name'))

        message.userName == "name"
        message.message == "message"
        message.updatedAt != null
        message.createdAt != null
        message.lastUpdated == '9999999999999999'
    }
}
