package jp.co.tads.room;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/***
 * STOMPのコンフィグクラスです。
 *
 * @author TAS
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * STOMPのエントリーポイントを登録します。
     *
     * @param registry エントリポイント登録
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("subscribe");
    }

    /**
     * Message Brokerの設定を行います。
     *
     * @param registry Message Broker Options
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/front");
        registry.enableSimpleBroker("/topic");
    }
}
