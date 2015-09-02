package jp.co.tads.room.app.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * ユーザのフォームクラスです。
 *
 * @author TAS
 */
@Data
public class UsersForm {

    /** ユーザID */
    @NotBlank(message = "{W0001}")
    @Size(max = 16, message = "{W0005}")
    private String id;

    /** ユーザ名 */
    @NotBlank(message = "{W0001}")
    @Size(max = 50, message = "{W0005}")
    private String name;

    /** パスワード */
    @NotBlank(message = "{W0001}")
    @Size(max = 16, message = "{W0005}")
    private String password;
}
