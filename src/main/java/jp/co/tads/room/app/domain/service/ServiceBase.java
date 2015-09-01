package jp.co.tads.room.app.domain.service;

import jp.co.tads.room.infra.jdbc.JdbcManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceBase {

    @Autowired
    public JdbcManager jdbcManager;
}
