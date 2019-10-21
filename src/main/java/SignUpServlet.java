import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SignUpServlet extends HttpServlet {

    public void init(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        DAOLoginPassword daoLoginPassword = new DAOLoginPassword();
        boolean isOk = daoLoginPassword.hasUser(login, password);
        if (isOk) {
            resp.sendRedirect("http://localhost:8080/lab3_war_exploded/sign_in");
        }
        else{
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            DAOLoginHash daoLoginHash = new DAOLoginHash();
            boolean isOk2 = daoLoginHash.hasHash(login, id);
            if (isOk2)
                resp.sendRedirect("http://localhost:8080/lab3_war_exploded/sign_in");
            else{
                UserPassword userPassword = new UserPassword(login, password);
                UserHash userHash = new UserHash(login, id);
                daoLoginPassword.addUser(userPassword);
                daoLoginHash.addHash(userHash);
                Cookie cookie = new Cookie("SessionId", id);
                resp.addCookie(cookie);
                resp.sendRedirect("http://localhost:8080/lab3_war_exploded/enter");
            }
        }
    }
}
