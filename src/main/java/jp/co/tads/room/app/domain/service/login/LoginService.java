package jp.co.tads.room.app.domain.service.login;

import static jp.co.tads.room.common.Factories.*;
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
        User param = new User();
        copy(loginForm, param);
        User user = jdbcManager.findOne(User.class,
                select(
                        User.ID,
                        User.NAME,
                        User.PASSWORD
                ).from(
                        User.TABLE_NAME
                ).where()
                        .eq(User.ID,        param.getId()).and()
                        .eq(User.PASSWORD,  param.getPassword())
        );

        if (user == null) {
            throw new AppException("アカウントID、パスワードが正しくありません。");
        }
        return user;
    }
}
