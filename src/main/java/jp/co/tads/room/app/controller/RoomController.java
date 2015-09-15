package jp.co.tads.room.app.controller;

import jp.co.tads.room.app.controller.base.ControllerBase;
import jp.co.tads.room.infra.security.LoginUserDetails;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * ルーム機能のコントローラクラスです。
 *
 * @author TAS
 */
@RequestMapping("room")
@Controller
public class RoomController extends ControllerBase {

    /**
     * ルーム画面を表示します。
     */
    @RequestMapping(method = RequestMethod.GET)
    String room(Model model, HttpSession session) {
        SecurityContext sc = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        LoginUserDetails loginUserDetails = (LoginUserDetails) sc.getAuthentication().getPrincipal();

        setPageTitle(model, loginUserDetails.getUser().getName());
    	model.addAttribute("loginUserId", loginUserDetails.getUser().getId());
        return "room/room";
    }
}
