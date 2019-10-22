import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

public class DAOLoginPassword {

    private Session session;



    public DAOLoginPassword() {

        //TODO зачем? все равно в методах открываешь свою сессию
        this.session = HibernateSessionFactoryUtil.getSession();

    }


    public void addUser(UserPassword user) {
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }



    public boolean hasUser(String login, String password) {
        boolean mark = false;
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction(); //TODO зачем в этом методе транзакция?
        UserPassword user = new UserPassword(login, password);
        //TODO тут падало потому что метод session.get получает вторым аргументом id, а ты отправляешь ему логин
        UserPassword user1;
        try {
            user1 = session.createQuery("From UserPassword Where login='" + login + "'", UserPassword.class).getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
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
        Transaction tx1 = session.beginTransaction(); //TODO зачем в этом методе транзакция?
        List lps = session.createQuery("From UserPassword").getResultList();
        tx1.commit();
        session.close();
        return lps;

    }
}
