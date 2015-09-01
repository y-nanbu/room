package jp.co.tads.room.app.controller;

import static jp.co.tads.room.common.Factories.*;

import jp.co.tads.room.app.controller.base.ControllerBase;
import jp.co.tads.room.app.domain.model.User;
import jp.co.tads.room.app.domain.service.login.LoginService;
import jp.co.tads.room.app.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ログイン画面のコントローラクラスです。
 *
 * @author TAS
 */
@Controller
@RequestMapping("login")
public class LoginController extends ControllerBase {

    @Autowired
    LoginService service;

    @ModelAttribute
    LoginForm setUpForm() {
        return new LoginForm();
    }

    /**
     * ログインフォーム画面を表示します。
     *
     * @return 表示するHTMLファイルのパス
     */
    @RequestMapping(method = RequestMethod.GET)
    String loginForm(Model model) {
        return "login/loginForm";
    }

    /**
     * 認証処理を実行します。
     *
     * @return リダイレクトパス
     */
    @RequestMapping(value = "auth", method = RequestMethod.POST)
    String authentication(@Validated LoginForm loginForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            setErrorMessages(model, list(accessor.getMessage("W0002")));
            return loginForm(model);
        }

        User user = service.findUser(loginForm);
        if (user == null) {
            setErrorMessages(model, list(accessor.getMessage("W0003")));
            return loginForm(model);
        }

        userDetails.init(user);
        return "redirect:/login";
    }
}
