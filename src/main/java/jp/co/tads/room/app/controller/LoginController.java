package jp.co.tads.room.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ログイン画面のコントローラクラスです。
 *
 * @author TAS
 */
@Controller
@RequestMapping("")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    String loginForm() {
        return "login/loginForm";
    }
}
