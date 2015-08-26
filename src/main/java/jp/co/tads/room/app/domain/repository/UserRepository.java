package jp.co.tads.room.app.domain.repository;

import jp.co.tads.room.app.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ユーザ情報のリポジトリクラス。
 *
 * @author TAS
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}