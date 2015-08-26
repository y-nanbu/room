package jp.co.tads.room.app.domain.service.login;

import jp.co.tads.room.app.domain.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Spring SecurityのUserDetailsの拡張クラスです。
 *
 * @author TAS
 */
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {

    private final User user;

    public LoginUserDetails(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }
}
