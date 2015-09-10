package jp.co.tads.room.app.api;

import static jp.co.tads.room.common.Factories.*;

import jp.co.tads.room.app.controller.base.ControllerBase;
import jp.co.tads.room.common.PropertiesUtils;
import jp.co.tads.room.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 外部からWebSocketに介入するためのコントローラクラスです。
 *
 * @author TAS
 */
@RestController
@RequestMapping("api/publish")
public class RestPublishController extends ControllerBase {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private PropertiesUtils props;

    @RequestMapping(method = RequestMethod.GET)
    public void sendMessage(
            @RequestParam("message") String message,
            @RequestParam("username") String username,
            @RequestParam("key") String key) {

        if (!props.getProperty("room.rest.key").equals(key)) {
            throw new AppException(accessor.getMessage("E0001"));
        }

        Map<String, String> pub = new HashMap<>();
        pub.put("message", message);
        pub.put("username", username);
        pub.put("timestamp", systimestamp().toString());
        this.template.convertAndSend("/topic/send", toJsonString(pub));
    }
}
