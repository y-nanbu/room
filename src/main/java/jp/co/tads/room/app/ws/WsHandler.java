package jp.co.tads.room.app.ws;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket通信のハンドラーです。
 *
 * @author TAS
 */
public class WsHandler extends TextWebSocketHandler {

    /** セッション一覧 */
    private Map<String, WebSocketSession> sessionMap_ = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.sessionMap_.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        this.sessionMap_.remove(session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 接続されているセッション（自分も含め）に転送する
        for (Map.Entry<String, WebSocketSession> entry : this.sessionMap_.entrySet()) {
            entry.getValue().sendMessage(message);
        }
    }
}
