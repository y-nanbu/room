package jp.co.tads.room.app.domain.service.login;

import static jp.co.tads.room.infra.jdbc.SqlBuilder.*;

import jp.co.tads.room.app.domain.model.User;
import jp.co.tads.room.app.form.LoginForm;
import jp.co.tads.room.exception.AppException;
import jp.co.tads.room.infra.jdbc.JdbcManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ログイン機能を提供するサービスクラスです。
 *
 * @author TAS
 */
@Service
public class LoginService {

    @Autowired
    JdbcManager jdbcManager;

    /**
     * 認証処理を行います。
     *
     * @param loginForm リクエストフォーム
     * @return 認証許可したユーザ情報
     */
    public User authenticate(LoginForm loginForm) {
        User user = jdbcManager.findOne(User.class,
                select()
                        .columns(
                                User.ID,
                                User.NAME,
                                User.PASSWORD
                        )
                        .from(
                                User.TABLE_NAME
                        )
                        .where()
                        .eq(User.ID,        loginForm.getId()).and()
                        .eq(User.PASSWORD,  loginForm.getPassword())
        );

        if (user == null || !user.getPassword().equals(loginForm.getPassword())) {
            throw new AppException("アカウントID、パスワードが正しくありません。");
        }
        return user;
    }
}
