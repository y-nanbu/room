package jp.co.tads.room.exception;

/**
 * アプリケーションにおける非検査例外クラスの拡張クラスです。
 *
 * <p>
 *     この例外は，利用者の操作によって回復可能な例外に対して利用してください。
 * </p>
 *
 * @author TAS
 */
public class AppException extends RuntimeException {

    public AppException() {
        super();
    }

    public AppException(Throwable e) {
        super(e);
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable e) {
        super(message, e);
    }
}
