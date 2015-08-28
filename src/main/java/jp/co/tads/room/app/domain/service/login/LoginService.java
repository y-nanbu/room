package jp.co.tads.room.app.domain.service.login;

import jp.co.tads.room.app.form.LoginForm;
import jp.co.tads.room.exception.AppException;
import org.springframework.stereotype.Service;

/**
 * ログイン機能を提供するサービスクラスです。
 *
 * @author TAS
 */
@Service
public class LoginService {

    /**
     * 認証処理を行います。
     *
     * @param loginForm リクエストフォーム
     */
    public void authenticate(LoginForm loginForm) {
        throw new AppException("現在，利用できない機能です。");
    }
}
