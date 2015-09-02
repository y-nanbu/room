package jp.co.tads.room.app.domain.service;

import jp.co.tads.room.infra.jdbc.JdbcManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

/**
 * サービスクラスの基底クラスです。
 *
 * @author TAS
 */
@Service
public class ServiceBase {

    @Autowired
    public JdbcManager jdbcManager;

    @Autowired
    public MessageSourceAccessor accessor;
}
