package jp.co.tads.room.app.controller.base;

import jp.co.tads.room.infra.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * コントローラクラスの基底クラスです。
 * ROOMアプリケーションのコントローラクラスは
 * すべてこのクラスを継承しなければなりません。
 *
 * @author TAS
 */
public class ControllerBase {

    /** ログインユーザ情報 */
    @Autowired
    public UserDetails userDetails;

    /** メッセージアクセサ */
    @Autowired
    public MessageSourceAccessor accessor;

    /**
     * 画面に表示させるメッセージを設定します。
     *
     * @param model モデル
     * @param messages メッセージのリスト
     */
    protected void setMessages(Model model, List<String> messages) {
        model.addAttribute("messages", messages);
    }

    /**
     * リダイレクト先の画面に表示するメッセージを登録します。
     *
     * @param redirectAttributes リダイレクトアトリビュート
     * @param messages メッセージのリスト
     */
    protected void setRedirectMessages(RedirectAttributes redirectAttributes, List<String> messages) {
        redirectAttributes.addFlashAttribute("messages", messages);
    }

    /**
     * 画面に表示させるエラーメッセージを設定します。
     *
     * @param model モデル
     * @param messages メッセージのリスト
     */
    protected void setErrorMessages(Model model, List<String> messages) {
        model.addAttribute("errors", messages);
    }

    /**
     * 画面のタイトルを設定します。
     *
     * @param model モデル
     * @param pageTitle 画面名
     */
    protected void setPageTitle(Model model, String pageTitle) {
        model.addAttribute("pageTitle", pageTitle);
    }
}
