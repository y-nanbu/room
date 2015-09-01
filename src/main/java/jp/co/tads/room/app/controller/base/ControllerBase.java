package jp.co.tads.room.app.controller.base;

import jp.co.tads.room.infra.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.ui.Model;
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

    protected void setErrorMessages(Model model, List<String> messages) {
        model.addAttribute("errors", messages);
    }
}
