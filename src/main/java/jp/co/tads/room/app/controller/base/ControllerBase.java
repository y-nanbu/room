package jp.co.tads.room.app.controller.base;

import jp.co.tads.room.infra.session.Flash;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * コントローラクラスの基底クラスです。
 *
 * @author TAS
 */
public class ControllerBase {

    @ModelAttribute
    Flash setUpFlash() {
        return new Flash();
    }
}
