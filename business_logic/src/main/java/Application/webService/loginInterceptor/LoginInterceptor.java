package Application.webService.loginInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getSession().getAttribute("user") != null) {
            return true;
        }

        if(request.getRequestURI().equals("/")) {
            response.sendRedirect("/landing");
        }else {
            response.sendRedirect("/login");
        }


        return false;
    }
}
