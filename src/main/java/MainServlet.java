import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.net.ssl.SSLEngine;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    @Override
    public void init(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,  resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String login = req.getParameter("login");
        String password = req.getParameter("password");
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        DAOLoginPassword lp = new DAOLoginPassword();
        if (lp.isTrue(login, password)){
            //  TODO добавить куки и тд
        }*/
        Session session = HibernateSessionFactoryUtil.getSession();
        System.out.println("done");
        req.getRequestDispatcher("login.jsp").forward(req,  resp);
        session.close();
    }
}
