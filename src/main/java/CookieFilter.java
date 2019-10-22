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
            if ((((HttpServletRequest) servletRequest).getRequestURI().equals("http://localhost:8080/lab3_war_exploded/users")))
                ((HttpServletResponse) servletResponse).sendRedirect("http://localhost:8080/lab3_war_exploded/welcome");
            else
                filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SessionId")) {
                        DAOLoginHash daoLoginHash = new DAOLoginHash();
                        boolean bool = daoLoginHash.isHashContainInTable(cookie.getValue());
                        if (bool)
                            request.getRequestDispatcher("users.jsp").forward(request, response);
                    }
                }
            }
        filterChain.doFilter(request, response);
        }

    }

