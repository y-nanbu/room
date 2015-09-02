package jp.co.tads.room.infra.filter;


import static jp.co.tads.room.common.StringUtil.*;
import jp.co.tads.room.infra.security.UserDetails;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * セッションの状態を確認するフィルタークラスです。
 *
 * @author TAS
 */
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        UserDetails userDetails = (UserDetails) session.getAttribute("scopedTarget.userDetails");

        String contextPath = httpServletRequest.getContextPath();
        String uri = httpServletRequest.getRequestURI();

        if (isAnyStartWith(uri, "/login", "/webjar", "/css", "/js", "/users/new", "/users/create")) {
            chain.doFilter(request, response);
        } else {
            if (userDetails == null) {
                httpServletResponse.sendRedirect(contextPath + "/login");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
