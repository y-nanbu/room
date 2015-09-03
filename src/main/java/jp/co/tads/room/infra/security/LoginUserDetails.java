package jp.co.tads.room.infra.security;

import jp.co.tads.room.app.domain.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

/**
 * {@inheritDoc}
 */
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public LoginUserDetails(User user) {
        super(user.getId(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }
}
