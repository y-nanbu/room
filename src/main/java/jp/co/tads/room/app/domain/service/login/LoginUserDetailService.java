package jp.co.tads.room.app.domain.service.login;

import jp.co.tads.room.app.domain.model.User;
import jp.co.tads.room.app.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ユーザ認証を行うサービスクラスです。
 *
 * @author h-hasegawa
 */
@Service
public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Not found user.");
        }
        return new LoginUserDetails(user);
    }
}