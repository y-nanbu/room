package jp.co.tads.room.common

import jp.co.tads.room.App
import jp.co.tads.room.infra.jdbc.JdbcManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(
        loader = SpringApplicationContextLoader.class,
        classes = App,
        locations = ['classpath:AppTestBean.groovy']
)
@WebIntegrationTest
class SpockBase extends Specification {

    @Autowired
    JdbcTemplate jdbcTemplate

    @Autowired
    JdbcManager jdbcManager
}
