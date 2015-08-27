package jp.co.tads.room.infra.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

/**
 * リクエストスコープを持つクラスです。
 *
 * @author TAS
 */
@Component
@Scope(
        proxyMode = ScopedProxyMode.TARGET_CLASS,
        value = WebApplicationContext.SCOPE_REQUEST)
public class Flash extends HashMap {
    public Flash() {super();}
}
