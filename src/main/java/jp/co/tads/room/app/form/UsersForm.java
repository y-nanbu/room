package jp.co.tads.room.app.form;

import lombok.Data;

/**
 * ユーザのフォームクラスです。
 *
 * @author TAS
 */
@Data
public class UsersForm {

    /** ユーザID */
    private String id;

    /** ユーザ名 */
    private String name;

    /** パスワード */
    private String password;
}
