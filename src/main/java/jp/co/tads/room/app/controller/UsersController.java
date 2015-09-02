package jp.co.tads.room.app.controller;

import static jp.co.tads.room.common.Factories.*;

import jp.co.tads.room.app.controller.base.ControllerBase;
import jp.co.tads.room.app.domain.service.users.UsersService;
import jp.co.tads.room.app.form.UsersForm;
import jp.co.tads.room.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ユーザ情報のコントローラクラスです。
 *
 * @author TAS
 */
@Controller
@RequestMapping("users")
public class UsersController extends ControllerBase {

    @Autowired
    UsersService service;

    @ModelAttribute
    UsersForm setUpForm() {
        return new UsersForm();
    }

    /**
     * ユーザの新規作成画面を返します。
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    String newUser(Model model) {
        setPageTitle(model, "ユーザ新規作成");
        return "users/new";
    }

    /**
     * ユーザの新規作成処理を行います。
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(@Validated UsersForm usersForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            setErrorMessages(model, list(accessor.getMessage("W0002")));
            return newUser(model);
        }

        try {
            service.createUser(usersForm);
        } catch (AppException e) {
            setErrorMessages(model, list(e.getMessage()));
            return newUser(model);
        }

        setRedirectMessages(redirectAttributes, list(accessor.getMessage("I0001")));
        return "redirect:/login";
    }
}
