package jp.co.tads.room.app.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * ユーザのフォームクラスです。
 *
 * @author TAS
 */
@Data
public class UsersForm {

    /** ユーザID */
    @NotBlank(message = "{W0001}")
    private String id;

    /** ユーザ名 */
    @NotBlank(message = "{W0001}")
    private String name;

    /** パスワード */
    @NotBlank(message = "{W0001}")
    private String password;
}
