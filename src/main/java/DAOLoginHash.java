import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

public class DAOLoginHash {
    private Session session;

    public DAOLoginHash(){
        this.session = HibernateSessionFactoryUtil.getSession();
    }

    public void addHash (UserHash userHash){
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(userHash);
        tx1.commit();
        session.close();
    }

    public boolean hasHash(String login, String hash){
        boolean mark = false;
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        UserHash user = new UserHash(login, hash);
        UserHash user1;
        try {
            user1 = session.createQuery("From UserHash Where login='" + login + "'", UserHash.class).getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        if (user1 != null) {
            if (user1.getHash().equals(hash))
                mark = true;
        }
        tx1.commit();
        session.close();
        return mark;
    }

    public boolean isHashContainInTable(String hash) {

        boolean isContain = false;

        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();


        List<UserHash> lhs = (List<UserHash>) session.createQuery("From UserHash").list();

        for (UserHash lh : lhs) {

            if (lh.getHash().equals(hash))

                isContain = true;

        }

        return isContain;

    }
}
