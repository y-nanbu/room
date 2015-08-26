package jp.co.tads.room.app.api;

import static jp.co.tads.room.common.Factories.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * WEB APIのサンプルコントローラーです。
 *
 * @author h-hasegawa
 */
@RestController
@RequestMapping("api/sample")
public class SampleApiController {

    /**
     * "HELLO"という文字列をレスポンスとして返します。
     *
     * @return レスポンス結果
     */
    @ResponseBody
    @RequestMapping(
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String hello() {
        return createJsonString("HELLO!!");
    }

}
