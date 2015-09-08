package jp.co.tads.room.app.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * メッセージに関するAPIを提供します。
 *
 * @author TAS
 */
@RestController
@RequestMapping("api/messages")
public class MessagesApiController {

    @ResponseBody
    @RequestMapping(
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String post() {
        return null;
    }
}
