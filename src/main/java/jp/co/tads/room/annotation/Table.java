package jp.co.tads.room.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * モデルクラスのクラスに対して付与するアノテーションです。
 * 対象のモデルクラスと関連するデータベースのテーブル名を設定します。
 *
 * @author TAS
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
    String value();
}
