import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO фильтр не работает как надо (опять могу открыть ссылки, которые не должны открываться)
// смотри фильтр из второй лабы
public class CookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            if ((((HttpServletRequest) servletRequest).getRequestURI().equals("/lab3_war_exploded/enter"))) {
                ((HttpServletResponse) servletResponse).sendRedirect("http://localhost:8080/lab3_war_exploded/welcome");
                return;
            }
            else
                filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            int counter = 0;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SessionId")) {
                        String s = request.getRequestURI();
                    if (request.getRequestURI().equals("/lab3_war_exploded/welcome")) {
                        response.sendRedirect("http://localhost:8080/lab3_war_exploded/enter");
                        return;
                    }
                    else if (request.getRequestURI().equals("/lab3_war_exploded/sign_in")) {
                        response.sendRedirect("http://localhost:8080/lab3_war_exploded/enter");
                        return;
                    }
                    else if (request.getRequestURI().equals("/lab3_war_exploded/sign_up")) {
                        response.sendRedirect("http://localhost:8080/lab3_war_exploded/enter");
                        return;
                    }
                    }
                counter++;
                }
            if (counter < 2){
                if (request.getRequestURI().equals("/lab3_war_exploded/enter")) {
                    response.sendRedirect("http://localhost:8080/lab3_war_exploded/welcome");
                    return;
                }
            }
            if (!request.getRequestURI().equals("/lab3_war_exploded/enter") && !request.getRequestURI().equals("/lab3_war_exploded/welcome") && !request.getRequestURI().equals("/lab3_war_exploded/sign_in") && !request.getRequestURI().equals("/lab3_war_exploded/sign_up")) {
                response.sendRedirect("http://localhost:8080/lab3_war_exploded/welcome");
                return;
            }
        }
        filterChain.doFilter(request, response);
        }

    }