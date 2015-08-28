package jp.co.tads.room.app.domain.service.login;

import jp.co.tads.room.app.domain.model.User;
import jp.co.tads.room.app.form.LoginForm;
import jp.co.tads.room.exception.AppException;
import jp.co.tads.room.infra.jdbc.JdbcManager;
import jp.co.tads.room.infra.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> param = new HashMap<>();
        param.put(User.ID, loginForm.getId());
        param.put(User.PASSWORD, loginForm.getPassword());

        User user = jdbcManager.findOne(User.class, param);
        if (user == null || !user.getPassword().equals(loginForm.getPassword())) {
            throw new AppException("アカウントID、パスワードが正しくありません。");
        }
        return user;
    }
}
