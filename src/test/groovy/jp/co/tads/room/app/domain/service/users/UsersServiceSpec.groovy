package jp.co.tads.room.app.domain.service.users

import jp.co.tads.room.app.domain.model.User
import jp.co.tads.room.common.SpockBase
import jp.co.tads.room.infra.jdbc.SqlBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

import static jp.co.tads.room.common.Factories.*

class UsersServiceSpec extends SpockBase {

    @Autowired
    UsersService usersService;

    @Transactional
    @Rollback
    def "指定のユーザが存在しない場合はNULLが帰ってくるべき"() {
        expect:
        usersService.findByUserId("xxxxxxxxxx") == null
    }

    @Transactional
    @Rollback
    def "指定のユーザが存在する場合は場合はユーザ情報が取得できるべき"() {
        given:
        def values = [:]
        values.put(User.ID, "XXXXXXXXXX")
        values.put(User.NAME, "YYYYYYYYYY")
        values.put(User.PASSWORD, "**********")
        values.put(User.CREATED_AT, systimestamp())
        values.put(User.UPDATED_AT, systimestamp())
        values.put(User.LAST_UPDATED, "ZZZZZZZZZZ")
        jdbcManager.insert(SqlBuilder.into(User.TABLE_NAME).values(values))

        when:
        User actualUser = usersService.findByUserId("XXXXXXXXXX")

        then:
        actualUser != null
        actualUser.id == "XXXXXXXXXX"
    }
}
