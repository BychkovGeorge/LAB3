import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class SignInServlet extends HttpServlet {

    public void init(){}


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("sign_in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        DAOLoginPassword daoLoginPassword = new DAOLoginPassword();
        boolean isOk = daoLoginPassword.hasUser(login, password);
        if (isOk){
            String id = UUID.randomUUID().toString();
            DAOLoginHash daoLoginHash = new DAOLoginHash();
            UserHash userHash = new UserHash(login, id);
            daoLoginHash.addHash(userHash);
            //boolean isOk2 = daoLoginHash.hasHash(login, id);
            //
                Cookie cookie = new Cookie("SessionId", id);
                resp.addCookie(cookie);
                resp.sendRedirect("http://localhost:8080/lab3_war_exploded/enter");
            //}
        }
        else{
            resp.sendRedirect("http://localhost:8080/lab3_war_exploded/sign_up");
        }
    }
}
