package jp.co.tads.room.app.domain.service.users;

import static jp.co.tads.room.common.Factories.*;
import static jp.co.tads.room.infra.jdbc.SqlBuilder.*;

import jp.co.tads.room.app.domain.model.User;
import jp.co.tads.room.app.domain.service.ServiceBase;
import jp.co.tads.room.app.form.UsersForm;
import jp.co.tads.room.exception.AppException;
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
        values.put(User.PASSWORD,     paramUser.getPassword());
        values.put(User.UPDATED_AT,   systimestamp());
        values.put(User.CREATED_AT,   systimestamp());
        values.put(User.LAST_UPDATED, paramUser.getId());

        jdbcManager.insert(into(User.TABLE_NAME).values(values));
    }
}
