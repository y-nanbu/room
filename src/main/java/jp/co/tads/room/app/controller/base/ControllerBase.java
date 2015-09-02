package jp.co.tads.room.app.controller.base;

import jp.co.tads.room.infra.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.awt.ModalExclude;

import java.util.List;

/**
 * コントローラクラスの基底クラスです。
 *
 * @author TAS
 */
public class ControllerBase {

    @Autowired
    public UserDetails userDetails;

    @Autowired
    public MessageSourceAccessor accessor;

    protected void setMessages(Model model, List<String> messages) {
        model.addAttribute("messages", messages);
    }

    protected void setRedirectMessages(RedirectAttributes redirectAttributes, List<String> messages) {
        redirectAttributes.addFlashAttribute("messages", messages);
    }

    protected void setErrorMessages(Model model, List<String> messages) {
        model.addAttribute("errors", messages);
    }

    protected void setPageTitle(Model model, String pageTitle) {
        model.addAttribute("pageTitle", pageTitle);
    }
}
