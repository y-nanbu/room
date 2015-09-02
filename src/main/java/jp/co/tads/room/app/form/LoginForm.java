package jp.co.tads.room.app.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * ログイン画面のフォームクラスです。
 *
 * @author TAS
 */
@Data
public class LoginForm {

    /** ログインID */
    @NotBlank(message = "{W0001}")
    private String id;

    /** パスワード */
    @NotBlank(message = "{W0001}")
    private String password;

    private String ldap;
}
