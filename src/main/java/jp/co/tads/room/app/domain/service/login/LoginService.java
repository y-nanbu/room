package jp.co.tads.room.app.domain.service.login;

import jp.co.tads.room.app.domain.model.Account;
import jp.co.tads.room.app.form.LoginForm;
import jp.co.tads.room.exception.AppException;
import jp.co.tads.room.infra.jdbc.JdbcManager;
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
     */
    public void authenticate(LoginForm loginForm) {
        Map<String, Object> param = new HashMap<>();
        param.put(Account.ID, loginForm.getUsername());
        param.put(Account.PASSWORD, loginForm.getPassword());

        Account account = jdbcManager.findOne(Account.class, param);
        if (account == null) {
            throw new AppException("ユーザ名、パスワードが正しくありません。");
        }
    }
}
