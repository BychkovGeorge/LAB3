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
        req.getRequestDispatcher("welcome.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String choice = req.getParameter("choice");
       //TODO костыль, что ползователь вводит какое-то число
       //сделай две разные кнопки на странице, которые будут отправлять тебя в разные сервлеты напрямую
       //и этот метод можно будет удалить
       switch(choice){
           case "1":
               //TODO во всем проекте ссылки захардкоженны, так делать нельзя
               //у меня вот например на компе ссылка http://localhost:8080/lab3_1_war_exploded/sign_up
               //делай через req.getContextPath() как мы делали во воторой лабе
               resp.sendRedirect("http://localhost:8080/lab3_war_exploded/sign_up");
               return;
           case "2":
               resp.sendRedirect("http://localhost:8080/lab3_war_exploded/sign_in");
               return;
           default:
               resp.sendRedirect("http://localhost:8080/lab3_war_exploded/welcome");
       }
    }
}
