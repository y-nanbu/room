package jp.co.tads.room.app.domain.service.login;

import static jp.co.tads.room.common.Factories.*;
import static jp.co.tads.room.infra.jdbc.SqlBuilder.*;

import jp.co.tads.room.app.domain.model.User;
import jp.co.tads.room.app.domain.service.ServiceBase;
import jp.co.tads.room.app.form.LoginForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ログイン機能を提供するサービスクラスです。
 *
 * @author TAS
 */
@Service
@Transactional
public class LoginService extends ServiceBase {

    /**
     * ユーザ情報を1件検索します。
     *
     * @param loginForm リクエストフォーム
     * @return 検索結果
     */
    public User findUser(LoginForm loginForm) {
        User param = new User();
        copy(loginForm, param);
        return jdbcManager.findOne(User.class,
                select(
                        User.ID,
                        User.NAME,
                        User.PASSWORD,
                        User.CREATED_AT,
                        User.UPDATED_AT,
                        User.LAST_UPDATED
                ).from(
                        User.TABLE_NAME
                ).where()
                        .eq(User.ID,        param.getId()).and()
                        .eq(User.PASSWORD,  param.getPassword())
        );
    }
}
