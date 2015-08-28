package jp.co.tads.room.app.form;

import lombok.Data;

/**
 * ログイン画面のフォームクラスです。
 *
 * @author TAS
 */
@Data
public class LoginForm {

    private String id;

    private String password;

    private String ldap;
}
