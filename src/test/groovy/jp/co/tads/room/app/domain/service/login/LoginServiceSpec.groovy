package jp.co.tads.room.app.domain.service.login

import jp.co.tads.room.app.form.LoginForm
import jp.co.tads.room.common.SpockBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

/**
 * ログインサービスのスペッククラスです。
 *
 * @author TAS
 */
class LoginServiceSpec extends SpockBase {

    @Autowired
    public LoginService loginService

    @Transactional
    @Rollback
    def "ログイン情報が一件取得できること"() {
        setup:
        jdbcTemplate.execute("""
            INSERT INTO users (
                id, name, password, updated_at, created_at, last_updated
            ) VALUES (
                '9999',
                'username',
                'password',
                CURRENT_TIMESTAMP,
                CURRENT_TIMESTAMP,
                'test'
            )
        """);

        LoginForm loginForm = new LoginForm()
        loginForm.setId("9999")
        loginForm.setPassword("password")

        expect:
        loginService.findUser(loginForm) != null

    }

}
