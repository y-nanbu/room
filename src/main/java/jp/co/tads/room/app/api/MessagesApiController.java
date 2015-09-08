package jp.co.tads.room.app.api;

import jp.co.tads.room.app.controller.base.ControllerBase;
import jp.co.tads.room.app.domain.service.messages.MessagesService;
import jp.co.tads.room.infra.security.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * メッセージに関するAPIを提供します。
 *
 * @author TAS
 */
@RestController
@RequestMapping("api/messages")
public class MessagesApiController extends ControllerBase {

    @Autowired
    MessagesService service;

    @ResponseBody
    @RequestMapping(
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String post(@RequestParam String message, HttpSession session) {

        // TODO: hasegawa ここのユーザIDの取り方はもっといい方法がありそうだ
        SecurityContext sc = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        LoginUserDetails loginUserDetails = (LoginUserDetails) sc.getAuthentication().getPrincipal();

        service.addMessage(message, loginUserDetails.getUsername());
        return null;
    }
}
