package jp.co.tads.room.app.domain.service.login

import jp.co.tads.room.app.domain.model.User
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
    LoginService loginService

    @Transactional
    @Rollback
    def "ログイン情報が一件取得できること"() {
        setup:
        LoginForm loginForm = new LoginForm()
        loginForm.setId("1")
        loginForm.setPassword("hasegawa")

        when:
        User user = loginService.findUser(loginForm)

        then:
        user != null

    }

}
