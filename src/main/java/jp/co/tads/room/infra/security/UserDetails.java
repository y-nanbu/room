package jp.co.tads.room.infra.security;

import jp.co.tads.room.app.domain.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

/**
 * ユーザの状態情報をもつクラスです。本システムにユーザがログインした場合，
 * このクラスがログインユーザの情報を保持します。
 *
 * @author TAS
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS,
        value = WebApplicationContext.SCOPE_SESSION)
public class UserDetails  implements Serializable {

    private User user;

    /**
     * ユーザ情報を初期化します。
     *
     * @param user ユーザ情報
     */
    public void init(User user) {
        this.user = user;
    }

    /**
     * ユーザ情報を取得します。
     *
     * @return ユーザ情報
     */
    public User getUserPrincipal() {
        return user;
    }
}
