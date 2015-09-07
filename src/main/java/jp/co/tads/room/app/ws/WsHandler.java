package jp.co.tads.room.app.ws;

import static jp.co.tads.room.common.Factories.*;

import jp.co.tads.room.app.domain.model.User;
import jp.co.tads.room.infra.security.LoginUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket通信のハンドラーです。
 *
 * @author TAS
 */
@Slf4j
public class WsHandler extends TextWebSocketHandler {

    /** セッション一覧 */
    private Map<String, WebSocketSession> sessionMap_ = new ConcurrentHashMap<>();

    private static final String CATEGORY = "category";

    private static final String DATA = "data";

    private static final String USER_ID = "userId";

    private static final String USER_NAME = "userName";

    private static final String TIMESTAMP = "timestamp";

    private static final String CONNECT = "CONNECT";

    private static final String DISCONNECT = "DISCONNECT";

    private static final String MESSAGE = "MESSAGE";

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String wsSessionId = session.getId();
        this.sessionMap_.put(wsSessionId, session);

        for (Map.Entry<String, WebSocketSession> entry : this.sessionMap_.entrySet()) {
            if (wsSessionId.equals(entry.getValue().getId())) {
                for (Map.Entry<String, WebSocketSession> connected : this.sessionMap_.entrySet()) {
                    Map<String, String> message = new HashMap<>();
                    message.put(CATEGORY, CONNECT);
                    message.put(DATA, getUser(connected.getValue()).getName());
                    message.put(USER_ID, getUser(connected.getValue()).getId());
                    entry.getValue().sendMessage(new TextMessage(toJsonString(message)));
                }
            } else {
                Map<String, String> message = new HashMap<>();
                message.put(CATEGORY, CONNECT);
                message.put(DATA, getUser(session).getName());
                message.put(USER_ID, getUser(session).getId());
                entry.getValue().sendMessage(new TextMessage(toJsonString(message)));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        this.sessionMap_.remove(session.getId());

        for (Map.Entry<String, WebSocketSession> entry : this.sessionMap_.entrySet()) {
            Map<String, String> message = new HashMap<>();
            message.put(CATEGORY, DISCONNECT);
            message.put(USER_ID, getUser(session).getId());
            entry.getValue().sendMessage(new TextMessage(toJsonString(message)));
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        User user = getUser(session);
        Map<String, String> sendMessage = new HashMap<>();
        sendMessage.put(CATEGORY, MESSAGE);
        sendMessage.put(DATA, message.getPayload());
        sendMessage.put(USER_NAME, user.getName());
        sendMessage.put(TIMESTAMP, systimestamp().toString());

        for (Map.Entry<String, WebSocketSession> entry : this.sessionMap_.entrySet()) {
            entry.getValue().sendMessage(new TextMessage(toJsonString(sendMessage)));
        }
    }

    private User getUser(WebSocketSession session) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) session.getPrincipal();
        LoginUserDetails loginUserDetails = (LoginUserDetails) token.getPrincipal();
        return loginUserDetails.getUser();
    }
}
