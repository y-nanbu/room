package jp.co.tads.room.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * モデルクラスのフィールドに設定するアノテーションです。
 * 対象のモデルクラスに対して，データベースでいうところのPKを表すフィールドに付与します。
 *
 * @author TAS
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Id {
}
