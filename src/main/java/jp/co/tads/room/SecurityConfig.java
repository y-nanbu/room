package jp.co.tads.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.List;

/**
 * 認証・認可に関するコンフィグクラスです。
 *
 * @author TAS
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    Environment env;

    /**
     * リクエストに対するセキュリティ設定を行います。
     *
     * @param web WebSecurity
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        String[] ignoreUriList = new String[6];
        ignoreUriList[0] = "/font/**";
        ignoreUriList[1] = "/css/**";
        ignoreUriList[2] = "/js/**";
        ignoreUriList[3] = "/api/**";
        ignoreUriList[4] = "/webjars/**";

        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        if (!profiles.contains("production") || !profiles.contains("staging")) {
            ignoreUriList[5] = "/env/**";
        }

        web.ignoring().antMatchers(ignoreUriList);
    }

    /**
     * 認証・認可の設定および、ログイン・ログアウトに関する設定を行います。
     *
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/")
                .failureUrl("/?error")
                .defaultSuccessUrl("/room", true)
                .usernameParameter("username")
                .passwordParameter("password")
                .and();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
                .logoutSuccessUrl("/");
    }

    @Configuration
    static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

        @SuppressWarnings("SpringJavaAutowiringInspection")
        @Autowired
        UserDetailsService userDetailsService;

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }
    }

}
