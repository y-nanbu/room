package jp.co.tads.room.infra.security;

import jp.co.tads.room.app.domain.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS,
        value = WebApplicationContext.SCOPE_SESSION)
public class UserDetails  implements Serializable {

    private User user;

    public void init(User user) {
        this.user = user;
    }

    public User getUserPrincipal() {
        return user;
    }
}
