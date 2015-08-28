package jp.co.tads.room;

import jp.co.tads.room.infra.jdbc.JdbcManager;
import jp.co.tads.room.infra.jdbc.JdbcManagerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * アプリケーションのエントリ・ポイントです。
 *
 * @author TAS
 */
@SpringBootApplication
public class App {

    /**
     * アプリケーションを実行します。
     *
     * <p>
     *     アプリケーションの実行時にプロパティ値を指定の値で上書きしたい場合は，
     *     実行時引数としてプロパティ値を設定してください。
     * </p>
     *
     * <p>
     *     また，--spring.profiles.active=[環境名] を実行時引数に指定することで，
     *     読み取るpropertyファイルを変えてアプリケーションを実行できます。
     *     もしくは環境変数でSPRING_PROFILES_ACTIVEを設定しても同じことが可能です。
     * </p>
     *
     * 使用例：
     * <pre>
     * java -jar app.jar --spring.profiles.active=dev --tmp.dir=/tmp/
     * </pre>
     *
     * @param args 実行時引数
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public JdbcManager jdbcManager() {
        return new JdbcManagerImpl();
    }
}
