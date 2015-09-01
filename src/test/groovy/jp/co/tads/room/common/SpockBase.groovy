package jp.co.tads.room.common

import jp.co.tads.room.infra.jdbc.JdbcManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration("classpath:AppTestBean.groovy")
class SpockBase extends Specification {

    @Autowired
    JdbcTemplate jdbcTemplate

    @Autowired
    JdbcManager jdbcManager
}
