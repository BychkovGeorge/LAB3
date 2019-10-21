import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EnterServlet extends HttpServlet {

    public void init(){}


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOLoginPassword daoLoginPassword = new DAOLoginPassword();
        List<UserPassword> list = daoLoginPassword.toList();
        String s = "";
        for (int i = 0; i < list.size(); i++){
            s += list.get(i).getLogin() + "<br>";
        }
        req.setAttribute("s", s);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}
