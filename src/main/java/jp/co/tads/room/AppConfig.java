package jp.co.tads.room;

import jp.co.tads.room.infra.interceptor.RoomAppRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * アプリケーションのコンフィグクラスです。
 *
 * @author TAS
 */
@Configuration
public class AppConfig {

    @Bean
    public RoomAppRequestInterceptor roomAppInterceptor() {
        return new RoomAppRequestInterceptor();
    }

    @Bean
    public MappedInterceptor interceptor() {
        return new MappedInterceptor(new String[]{"/**"}, roomAppInterceptor());
    }
}
