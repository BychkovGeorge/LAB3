import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DAOLoginPassword {

    private Session session;



    public DAOLoginPassword() {

        this.session = HibernateSessionFactoryUtil.getSession();

    }
    public void addUser(UserPassword user) {
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }



    public boolean isTrue(String login, String password) {
        boolean mark = false;
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        UserPassword user = new UserPassword(login, password);
        UserPassword user1 = session.get(UserPassword.class, login);
        if (user1 != null) {
            if (user1.getPassword().equals(password))
                mark = true;
        }
        tx1.commit();
        session.close();
        return mark;
    }

    public List<UserPassword> toList() {

        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        List<UserPassword> lps = (List<UserPassword>) session.createQuery("From UserPassword").list();
        tx1.commit();
        session.close();
        return lps;

    }
}
