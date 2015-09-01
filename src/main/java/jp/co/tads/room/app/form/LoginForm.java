package jp.co.tads.room.app.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * ログイン画面のフォームクラスです。
 *
 * @author TAS
 */
@Data
public class LoginForm {

    @NotBlank(message = "{W0001}")
    private String id;

    @NotBlank(message = "{W0001}")
    private String password;

    private String ldap;
}
