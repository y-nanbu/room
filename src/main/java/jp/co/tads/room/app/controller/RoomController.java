package jp.co.tads.room.app.controller;

import jp.co.tads.room.app.controller.base.ControllerBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    String room(Model model) {
        setPageTitle(model, "");
        return "room/room";
    }
}
