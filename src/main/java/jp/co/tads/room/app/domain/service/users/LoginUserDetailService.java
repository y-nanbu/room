package jp.co.tads.room.app.domain.service.users;

import jp.co.tads.room.app.domain.model.User;
import jp.co.tads.room.infra.security.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = usersService.findByUserId(id);
        if (user == null) {
            throw new UsernameNotFoundException("Not found user.");
        }
        return new LoginUserDetails(user);
    }
}
