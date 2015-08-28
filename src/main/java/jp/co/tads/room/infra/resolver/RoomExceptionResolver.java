package jp.co.tads.room.infra.resolver;

import jp.co.tads.room.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * アプリケーションの例外ハンドリングクラスです。
 *
 * @author TAS
 */
@Component
@Slf4j
public class RoomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (AppException.class == ex.getClass()) {
            List<String> errors = new ArrayList<>();
            errors.add(ex.getMessage());

            FlashMap flashMap = new FlashMap();
            flashMap.put("errors", errors);
            FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
            flashMapManager.saveOutputFlashMap(flashMap, request, response);

            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:");
            return mav;
        }
        return null;
    }
}
