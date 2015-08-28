package jp.co.tads.room.app.controller.base;

import jp.co.tads.room.infra.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * コントローラクラスの基底クラスです。
 *
 * @author TAS
 */
public class ControllerBase {

    @Autowired
    public UserDetails userDetails;
}
