package jp.co.tads.room.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * プロパティ値を取得するユーティリティクラスです。
 *
 * @author TAS
 */
@Service
public final class PropertiesUtils {

    @Autowired
    Environment environment;

    private PropertiesUtils() {
    }

    public String getProperty(String key) {
        return environment.getProperty(key);
    }
}
