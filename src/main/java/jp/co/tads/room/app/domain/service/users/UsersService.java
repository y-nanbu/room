package jp.co.tads.room.app.domain.service.users;

import static jp.co.tads.room.common.Factories.*;
import static jp.co.tads.room.infra.jdbc.SqlBuilder.*;

import jp.co.tads.room.app.domain.model.User;
import jp.co.tads.room.app.domain.service.ServiceBase;
import jp.co.tads.room.app.form.UsersForm;
import jp.co.tads.room.exception.AppException;
import jp.co.tads.room.infra.jdbc.SqlBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * ユーザのサービスクラスです。
 *
 * @author TAS
 */
@Service
@Transactional
public class UsersService extends ServiceBase {

    /**
     * ユーザIDよりユーザ情報を取得します。
     * @param id ユーザID
     * @return
     */
    public User findByUserId(String id) {
        return jdbcManager.findOne(User.class,
                SqlBuilder.select(
                        User.ID,
                        User.NAME,
                        User.PASSWORD,
                        User.UPDATED_AT,
                        User.CREATED_AT,
                        User.LAST_UPDATED
                ).from(
                        User.TABLE_NAME
                ).where()
                        .eq(User.ID, id)
        );
    }

    /**
     * ユーザを作成します。
     *
     * <p>
     *     既に存在するユーザIDが指定されている場合は，
     *     {@link jp.co.tads.room.exception.AppException AppException}が発生します。
     * </p>
     *
     * <p>
     *     本メソッドを呼び出す場合，適切にAppExceptionを処理してください。
     * </p>
     *
     * @param form リクエストフォーム
     */
    public void createUser(UsersForm form) {
        User paramUser = new User();
        BeanUtils.copyProperties(form, paramUser);

        User user = jdbcManager.findOne(User.class,
                select(
                        User.ID
                ).from(
                        User.TABLE_NAME
                ).where()
                        .eq(User.ID, paramUser.getId()));

        if (user != null) {
            throw new AppException(accessor.getMessage("W0004"));
        }

        Map<String, Object> values = map();
        values.put(User.ID,           paramUser.getId());
        values.put(User.NAME,         paramUser.getName());
        values.put(User.PASSWORD,     passwordEncoder.encode(paramUser.getPassword()));
        values.put(User.UPDATED_AT,   systimestamp());
        values.put(User.CREATED_AT,   systimestamp());
        values.put(User.LAST_UPDATED, paramUser.getId());

        jdbcManager.insert(into(User.TABLE_NAME).values(values));
    }
}
