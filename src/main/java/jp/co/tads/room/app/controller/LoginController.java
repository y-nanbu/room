package jp.co.tads.room.app.controller;

import jp.co.tads.room.app.controller.base.ControllerBase;
import jp.co.tads.room.app.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ログイン画面のコントローラクラスです。
 *
 * @author TAS
 */
@Controller
@RequestMapping("")
public class LoginController extends ControllerBase {

    @ModelAttribute
    LoginForm setUpForm() {
        return new LoginForm();
    }

    /**
     * ログインフォーム画面を表示します。
     */
    @RequestMapping(method = RequestMethod.GET)
    String loginForm(Model model) {
        setPageTitle(model, "ログイン");
        return "login/loginForm";
    }
}
